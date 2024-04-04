package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.dto.announcement.additionInfo.AdditionComboValueDto;
import uz.tsx.dto.request.AdditionComboValueCreate;
import uz.tsx.dto.request.AdditionComboValueUpdate;
import uz.tsx.entity.announcement.additionInfo.AdditionComboValueEntity;

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
        AdditionComboValueDto dto = comboValue.toDto("group");

        dto.setGroup(AdditionGroupConvert.convertToDto(comboValue.getGroup()));

        return dto;
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
}
