package com.github.kotelkov.pms;

import com.github.kotelkov.pms.controller.Controller;
import com.github.kotelkov.pms.inject.ApplicationContext;
import com.github.kotelkov.pms.inject.DependencyController;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = DependencyController.createApplicationContext(Controller.class,"com.github");
        Controller controller = (Controller) applicationContext.getBean(Controller.class);
        controller.doTask();
    }
}


