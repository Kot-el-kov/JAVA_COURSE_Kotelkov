package com.github.kotelkov.pms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Mapper<Model,Dto> implements GenericMapper<Model,Dto>{

    private final ModelMapper modelMapper;

    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Model convertToModel(Dto dto, Model model) {
        return Objects.isNull(dto) ? null : (Model) modelMapper.map(dto,(Class<?>) model);
    }

    @Override
    public Dto convertToDto(Model model, Dto dto) {
        return Objects.isNull(model) ? null : (Dto) modelMapper.map(model,(Class<?>) dto);
    }

    @Override
    public List<Dto> convertListToDtoList(List<Model> modelList, Dto dto) {
        return modelList.stream()
                .map((model) -> (Dto) modelMapper.map(model,(Class<?>) dto))
                .collect(Collectors.toList());
    }

    @Override
    public List<Model> convertListToModelList(List<Dto> dtoList, Model model) {
        return dtoList.stream()
                .map((dto) -> (Model) modelMapper.map(dto,(Class<?>) model))
                .collect(Collectors.toList());
    }
}
