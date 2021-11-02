package com.github.kotelkov.pms;

import com.github.kotelkov.pms.inject.DependencyController;
import com.github.kotelkov.pms.inject.DependencyService;

import java.util.Map;

public final class ApplicationContext {
    private final Map<String, Object> applicationContext;

    public ApplicationContext(Map<String, Object> applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static Map<String, Object> getApplicationContext(Class<?> clazz) {
        DependencyController.setDependency(clazz);
        return DependencyService.getInstance().getInstanceClassMap();
    }
}
