package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.inject.annotation.Autowired;
import com.github.kotelkov.pms.inject.annotation.Component;
import com.github.kotelkov.pms.dao.DatabaseInterface;

@Component
public class Service implements ServiceInterface{

    @Autowired
    DatabaseInterface database;

    @Override
    public String doTask() {
        return database.doTask();
    }
}
