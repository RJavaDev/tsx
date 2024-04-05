package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.dto.announcement.additionInfo.AdditionGroupDto;
import uz.tsx.dto.request.AdditionGroupCreate;
import uz.tsx.dto.request.AdditionGroupUpdate;
import uz.tsx.entity.announcement.additionInfo.AdditionGroupEntity;

import java.util.List;

@UtilityClass
public class AdditionGroupConvert {

    public AdditionGroupEntity convertToEntity(AdditionGroupCreate dto){

        AdditionGroupEntity entity = new AdditionGroupEntity();

        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());
        entity.setCategoryId(dto.getCategoryId());
        entity.setType(dto.getType());

        return entity;
    }

    public AdditionGroupDto convertToDto(AdditionGroupEntity entity) {

        AdditionGroupDto dto = entity.toDto("category");

        dto.setCategoryDto(CategoryConvert.fromNoChild(entity.getCategory()));

        return dto;

    }

    public AdditionGroupEntity convertToEntity(AdditionGroupUpdate dto){

        AdditionGroupEntity entity = new AdditionGroupEntity();

        entity.setId(dto.getId());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());
        entity.setCategoryId(dto.getCategoryId());
        entity.setType(dto.getType());

        return entity;
    }

    public List<AdditionGroupDto> convertToDto(List<AdditionGroupEntity> entityList){
        return entityList.stream().map(AdditionGroupConvert::convertToDto).toList();
    }
}
