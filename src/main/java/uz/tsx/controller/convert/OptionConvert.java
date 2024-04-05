package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.dto.announcement.option.OptionDto;
import uz.tsx.dto.request.OptionCreateDto;
import uz.tsx.entity.announcement.option.OptionEntity;

import java.util.List;

@UtilityClass
public class OptionConvert {

    public OptionEntity convertToEntity(OptionCreateDto dto){
        OptionEntity entity = new OptionEntity();

        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setCategoryId(dto.getCategoryId());
        entity.setOptionGroupId(dto.getOptionGroupId());

        return entity;
    }

    public OptionDto convertToDto(OptionEntity entity){

        OptionDto dto = entity.toDto("category","optionGroup");
        dto.setCategory(CategoryConvert.fromNoTree(entity.getCategory()));
        dto.setOptionGroup(OperationGroupConvert.convertToDto(entity.getOptionGroup()));

        return dto;
    }

    public List<OptionDto> convertToDto(List<OptionEntity> entityList){
        return entityList.stream().map(OptionConvert::convertToDto).toList();
    }
}
