package com.github.kotelkov.pms.dao;

import com.github.kotelkov.pms.configuration.annotation.ConfigClass;
import com.github.kotelkov.pms.configuration.annotation.Value;
import com.github.kotelkov.pms.inject.annotation.Component;

@ConfigClass
@Component
public class Database implements DatabaseInterface{

    @Value(configName = "app.properties", propertyName = "my.param.db")
    private String someText;
    @Override
    public String doTask() {
        return someText;
    }
}
