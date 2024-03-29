package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.dto.announcement.option.OptionGroupDto;
import uz.tsx.dto.request.OptionGroupCreateDto;
import uz.tsx.entity.announcement.option.OptionGroupEntity;

import java.util.List;

@UtilityClass
public class OperationGroupConvert {

    public OptionGroupEntity convertToEntity(OptionGroupCreateDto dto){
        OptionGroupEntity entity = new OptionGroupEntity();

        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());

        return entity;
    }

    public OptionGroupDto convertToDto(OptionGroupEntity entity){
        return entity.toDto();
    }

    public List<OptionGroupEntity> convertToEntity(List<OptionGroupCreateDto> dto){
        return dto.stream().map(OperationGroupConvert::convertToEntity).toList();
    }

    public List<OptionGroupDto> convertToDto(List<OptionGroupEntity> entityList){
        return entityList.stream().map(OperationGroupConvert::convertToDto).toList();
    }

}
