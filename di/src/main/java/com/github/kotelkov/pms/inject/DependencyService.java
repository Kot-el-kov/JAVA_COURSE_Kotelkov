package com.github.kotelkov.pms.inject;

import com.github.kotelkov.pms.inject.annotation.Autowired;
import com.github.kotelkov.pms.inject.annotation.Component;
import com.github.kotelkov.pms.inject.annotation.Value;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class DependencyService {
    private static DependencyService instance;
    private final Map<String, Object> instanceClassMap;
    private Object instanceClass;
    private Class<?> configClass;

    private DependencyService() {
        this.instanceClassMap = new HashMap<>();
    }

    public static DependencyService getInstance() {
        if (instance == null) {
            instance = new DependencyService();
        }
        return instance;
    }

    public void setValue(Object instanceOfClass) throws IllegalAccessException {
        this.configClass = instanceOfClass.getClass();
        final Component annotation = configClass.getAnnotation(Component.class);
        if (!(annotation == null)) {
            addFieldValue(instanceOfClass);
        }
    }

    public void addFieldValue(Object instanceOfClass) throws IllegalAccessException {
        final List<Field> annotatedFields = Arrays.stream(configClass.getDeclaredFields())
                .filter(i -> i.isAnnotationPresent(Value.class))
                .collect(Collectors.toList());
        for (Field field : annotatedFields) {
            final Properties properties = PropertiesReader.getInstance().readProperties(field.getAnnotation(Value.class).configName().toLowerCase());
            final Object value = properties.getProperty(field.getAnnotation(Value.class).propertyName().toLowerCase());
            field.setAccessible(true);
            field.set(instanceOfClass, value);
            field.setAccessible(false);
        }
    }

    public void addToContext(Class<?> clazz) throws IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {
        if (clazz.getAnnotation(Component.class) == null) {
            throw new IllegalArgumentException("Class " + clazz.getSimpleName() + " don't have 'Component' com.github.kotelkov.pms.annotation");
        }
        final Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        this.instanceClass = constructor.newInstance();
        getInstance().setValue(instanceClass);
        constructor.setAccessible(false);
        instanceClassMap.put(clazz.getName(), instanceClass);
    }

    public void initConstructor(String path) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        final List<Field> annotatedFields = Arrays.stream(instanceClass.getClass().getDeclaredFields())
                .filter(i -> !i.getType().isPrimitive() && i.isAnnotationPresent(Autowired.class))
                .collect(Collectors.toList());
        for (Field field : annotatedFields) {
            final Class<?> implClass = DependencyInject.getInstance(path).injection(field);
            if (implClass.isAnnotationPresent(Component.class) && !instanceClassMap.containsKey(implClass.getName())) {
                final Object bufInstanceClass = this.instanceClass;
                addToContext(implClass);
                initConstructor(path);
                this.instanceClass = bufInstanceClass;
            }
            field.setAccessible(true);
            if (instanceClassMap.containsKey(implClass.getName())) {
                field.set(instanceClass, instanceClassMap.get(implClass.getName()));
                field.setAccessible(false);
            }
        }
    }

    public Map<String, Object> getInstanceClassMap() {
        return instanceClassMap;
    }
}