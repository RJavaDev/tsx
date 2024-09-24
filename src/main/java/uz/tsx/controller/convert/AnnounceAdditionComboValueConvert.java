package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.dto.announcement.additionInfo.AdditionComboValueDto;
import uz.tsx.dto.request.AdditionComboValueCreate;
import uz.tsx.dto.request.AdditionComboValueUpdate;
import uz.tsx.entity.announcement.additionInfo.AdditionComboValueEntity;

import java.util.List;

@UtilityClass
public class AnnounceAdditionComboValueConvert {

    public AdditionComboValueEntity convertToEntity(AdditionComboValueCreate dto){
        AdditionComboValueEntity entity = new AdditionComboValueEntity();

        entity.setGroupId(dto.getGroupId());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());

        return entity;
    }

    public AdditionComboValueDto convertToDto(AdditionComboValueEntity comboValue) {
        return comboValue.toDto("group");
    }

    public AdditionComboValueEntity convertToDto(AdditionComboValueUpdate updateDto) {

        AdditionComboValueEntity entity = new AdditionComboValueEntity();

        entity.setId(updateDto.getId());
        entity.setGroupId(updateDto.getGroupId());
        entity.setNameUz(updateDto.getNameUz());
        entity.setNameRu(updateDto.getNameRu());
        entity.setNameEn(updateDto.getNameEn());

        return entity;
    }

    public List<AdditionComboValueDto> convertToDto(List<AdditionComboValueEntity> comboValueById) {
        return comboValueById.stream().map(AnnounceAdditionComboValueConvert::convertToDto).toList();
    }
}
