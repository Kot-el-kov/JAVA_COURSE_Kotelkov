package com.github.kotelkov.pms.dao;

import com.github.kotelkov.pms.inject.annotation.Value;
import com.github.kotelkov.pms.inject.annotation.Component;

@Component
public class DatabaseImpl implements Database {
    @Value(configName = "app.properties", propertyName = "my.param.db")
    private String someText;
    @Override
    public String doTask() {
        return someText;
    }
}
