package com.github.kotelkov.pms.configuration;

import com.github.kotelkov.pms.inject.annotation.Value;
import com.github.kotelkov.pms.inject.annotation.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConfigService {
    private static ConfigService instance;
    private Class<?> configClass;
    private String configPath;

    public static ConfigService getInstance() {
        if (instance == null) {
            instance = new ConfigService();
        }
        return instance;
    }

    public void setValue(Object instanceOfClass) throws IllegalAccessException {
        this.configClass = instanceOfClass.getClass();
        final Component annotation = configClass.getAnnotation(Component.class);
        if (!(annotation == null)) {
            this.configPath = annotation.configPath();
            addFieldValue(instanceOfClass);
        }
    }

    public void addFieldValue(Object instanceOfClass) throws IllegalAccessException {
        final List<Field> annotatedFields = Arrays.stream(configClass.getDeclaredFields())
                .filter(i -> i.isAnnotationPresent(Value.class))
                .collect(Collectors.toList());
        for (Field field : annotatedFields) {
            final Config config = new Config(field);
            final Properties properties = ConfigReader.getInstance().readConfig(configPath + config.getConfigName());
            final Object value = properties.getProperty(config.getPropertyName());//customConverter(field, properties.getProperty(config.getPropertyName()));
            config.getField().setAccessible(true);
            config.getField().set(instanceOfClass, value);
            config.getField().setAccessible(false);
        }
    }

    private Object customConverter (Field field, String variable) {
        final String variableType = field.getType().getSimpleName();
        if (variableType.equals("boolean")){
            return Boolean.parseBoolean(variable);
        }
        else if (variableType.equals("int")){
            return Integer.parseInt(variable);
        }
        return variable;
    }
}
