package com.github.kotelkov.pms.mapper;

import java.util.List;

public interface GenericMapper<Model,Dto> {
    Model convertToModel(Dto dto, Model model);

    Dto convertToDto(Model model, Dto dto);

    List<Dto> convertListToDtoList(List<Model> modelList, Dto dto);

    List<Model> convertListToModelList(List<Dto> dtoList, Model model);
}
