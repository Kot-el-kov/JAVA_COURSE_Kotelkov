package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.inject.annotation.Autowired;
import com.github.kotelkov.pms.inject.annotation.Component;
import com.github.kotelkov.pms.dao.Database;

@Component
public class ServiceImpl implements Service {
    @Autowired
    private Database database;
    @Override
    public String doTask() {
        return database.doTask();
    }
}
