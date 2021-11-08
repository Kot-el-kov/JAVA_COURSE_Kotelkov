package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.inject.annotation.Autowired;
import com.github.kotelkov.pms.inject.annotation.Component;
import com.github.kotelkov.pms.service.Service;

@Component
public class Controller {
    @Autowired
    private Service service;
    public void doTask(){
        System.out.println(service.doTask());
    }
}
