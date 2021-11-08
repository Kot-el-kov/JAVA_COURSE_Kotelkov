package com.github.kotelkov.pms.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfiguration {
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
