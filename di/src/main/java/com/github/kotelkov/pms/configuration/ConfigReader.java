package com.github.kotelkov.pms.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader instance;
    private Properties properties;

    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public Properties readConfig(String path) {
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(path)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            System.out.println( "Property file not found");
        }
        return properties;
    }
}