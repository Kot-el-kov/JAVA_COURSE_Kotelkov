package com.github.kotelkov.pms;

import com.github.kotelkov.pms.controller.Controller;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        HashMap<String, Object> applicationContext = (HashMap<String, Object>) ApplicationContext.
                getApplicationContext(Controller.class);
        Controller controller = (Controller) applicationContext.get(Controller.class.getName());
        controller.doTask();
    }
}


