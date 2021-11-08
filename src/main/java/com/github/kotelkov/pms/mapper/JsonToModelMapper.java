package com.github.kotelkov.pms.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class JsonToModelMapper {

    private final ObjectMapper objectMapper;

    public JsonToModelMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    public Object convertToModel(String jsonString,Class modelClass){
        return objectMapper.readValue(jsonString,modelClass);
    }

    @SneakyThrows
    public String convertToJson(Object model){
        return objectMapper.writeValueAsString(model);
    }
}
