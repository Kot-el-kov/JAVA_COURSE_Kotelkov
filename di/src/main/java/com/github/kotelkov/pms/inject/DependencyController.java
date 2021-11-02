package com.github.kotelkov.pms.inject;

import java.lang.reflect.InvocationTargetException;

public class DependencyController {

    public static void setDependency(Class<?> clazz)  {
        try {
            DependencyService.getInstance().setVariable(clazz);
            DependencyService.getInstance().initConstructor();
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println("Error when trying to set dependency!");
        }
    }
}
