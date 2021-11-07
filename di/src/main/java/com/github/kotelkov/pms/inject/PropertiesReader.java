package com.github.kotelkov.pms.inject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static PropertiesReader instance;
    private Properties properties;

    public static PropertiesReader getInstance() {
        if (instance == null) {
            instance = new PropertiesReader();
        }
        return instance;
    }

    public Properties readProperties(String path) {
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(path)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            System.out.println( "Property file not found");
        }
        return properties;
    }
}