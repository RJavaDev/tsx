package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.constants.EntityStatus;
import uz.tsx.dto.RegionDto;
import uz.tsx.dto.request.RegionCreateRequestDto;
import uz.tsx.dto.request.RegionUpdateRequestDto;
import uz.tsx.entity.RegionEntity;

import java.util.List;


@UtilityClass
public class RegionConvert {
    public RegionEntity convertToEntity(RegionDto regionDto){
        return regionDto.toEntity();
    }

    public RegionEntity convertToEntity(RegionCreateRequestDto regionCreateRequestDto){
        RegionEntity region = new RegionEntity();
        region.setNameUz(regionCreateRequestDto.getNameUz());
        region.setNameRu(regionCreateRequestDto.getNameRu());
        region.setNameEn(regionCreateRequestDto.getNameEn());
        region.setParentId(regionCreateRequestDto.getParentId());
        return region;
    }

    public RegionEntity convertToEntity(RegionUpdateRequestDto regionUpdateRequestDto){
        RegionEntity region = new RegionEntity();
        region.setNameUz(regionUpdateRequestDto.getNameUz());
        region.setNameEn(regionUpdateRequestDto.getNameEn());
        region.setNameRu(regionUpdateRequestDto.getNameRu());
        region.setParentId(regionUpdateRequestDto.getParentId());
        return region;
    }

    public RegionDto from(RegionEntity region){
        return region.toDto();
}

    public RegionDto fromTree(RegionEntity region){

        RegionDto regionDto = region.toDto("children");
        regionDto.setChildren(fromTree(region.getChildren()));

        return regionDto;
    }

    public RegionDto fromNoTree(RegionEntity region){
        return region.toDto("children");
    }

    public List<RegionDto> fromNoTree(List<RegionEntity> regionEntityList){
        return regionEntityList.stream().map(RegionConvert::fromNoTree).toList();
    }

    public List<RegionDto> fromTree(List<RegionEntity> regionEntityList){
        return regionEntityList.stream().map(RegionConvert::fromTree)
                .filter(p -> p.getStatus() != EntityStatus.DELETED).toList();
    }
}
