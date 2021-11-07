package com.github.kotelkov.pms.inject;

import java.lang.reflect.InvocationTargetException;

public class DependencyController {
    public static ApplicationContext createApplicationContext(Class<?> clazz,String path) {
        DependencyController.initializeContext(clazz,path);
        ApplicationContext applicationContext = new ApplicationContext(DependencyService.getInstance().getInstanceClassMap());
        return applicationContext;
    }

    public static void initializeContext(Class<?> clazz,String path)  {
        try {
            DependencyService.getInstance().addToContext(clazz);
            DependencyService.getInstance().initConstructor(path);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println("Error when trying to set dependency!");
        }
    }
}
