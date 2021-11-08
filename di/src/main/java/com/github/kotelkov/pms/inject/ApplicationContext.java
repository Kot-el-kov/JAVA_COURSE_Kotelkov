package com.github.kotelkov.pms.inject;

import java.util.Map;

public class ApplicationContext {
    private final Map<String, Object> applicationContext;

    public ApplicationContext(Map<String, Object> applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Object getBean(Class obj){
        return applicationContext.get(obj.getName());
    }
}
