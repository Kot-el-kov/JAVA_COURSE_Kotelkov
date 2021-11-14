package com.github.kotelkov.pms.configuration;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
@PropertySource("classpath:/jdbc.properties")
@EnableAspectJAutoProxy
public class JdbcConfiguration {
    @Value("${database.url}")
    private String databaseUrl;
    @Value("${database.username}")
    private String databaseUserName;
    @Value("${database.password}")
    private String databasePassword;

    @SneakyThrows
    @Bean(destroyMethod = "close")
    public Connection connection(){
        return DriverManager.getConnection(databaseUrl,databaseUserName,databasePassword);
    }
}
