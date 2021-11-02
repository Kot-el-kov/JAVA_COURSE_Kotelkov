package com.github.kotelkov.pms.configuration;

import com.github.kotelkov.pms.configuration.annotation.Value;

import java.lang.reflect.Field;

public class Config {
    private final Field field;
    private final String configName;
    private final String propertyName;

    public Config(Field field) {
        this.field = field;
        this.configName = field.getAnnotation(Value.class).configName().toLowerCase();
        this.propertyName = field.getAnnotation(Value.class).propertyName().toLowerCase();
    }

    public Field getField() {
        return field;
    }

    public String getConfigName() {
        return configName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
