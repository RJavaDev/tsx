package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.constants.EntityStatus;
import uz.tsx.dto.CategoryDto;
import uz.tsx.dto.request.CategoryCreateRequestDto;
import uz.tsx.dto.request.CategoryUpdateRequestDto;
import uz.tsx.dto.response.CategoryResponseDto;
import uz.tsx.entity.AttachEntity;
import uz.tsx.entity.CategoryEntity;

import java.util.List;
import java.util.Objects;

@UtilityClass
public class CategoryConvert {

    public CategoryEntity convertToEntity(CategoryCreateRequestDto categoryDto){

        CategoryEntity category = new CategoryEntity();

        category.setNameEn(categoryDto.getNameEn());
        category.setNameRu(categoryDto.getNameRu());
        category.setNameUz(categoryDto.getNameUz());
        category.setParentId(categoryDto.getParentId());
        return category;
    }


    public CategoryEntity convertToEntity(CategoryUpdateRequestDto categoryDto){

        CategoryEntity category = new CategoryEntity();
        category.setId(categoryDto.getId());
        category.setNameUz(categoryDto.getNameUz());
        category.setNameRu(categoryDto.getNameRu());
        category.setNameEn(categoryDto.getNameEn());
        category.setParentId(categoryDto.getParentId());
        return category;
    }

    public CategoryDto fromTree(CategoryEntity category){

        CategoryDto dto = category.toDto("children");
        AttachEntity attachId = category.getAttach();
        if(Objects.nonNull(attachId)){
            dto.setAttach(AttachConvert.convertToAttachUrlDto(category.getAttach()));
        }
        dto.setChildren(fromTree(category.getChildren()));

        return dto;
    }

    public CategoryDto fromOneLevelChild(CategoryEntity entity){
        return entity.getDto(true);
    }

    public CategoryResponseDto fromOpenData(CategoryEntity category){
        CategoryResponseDto categoryResponseDto = fromOpenDataNoChild(category);
        categoryResponseDto.setChild(fromOpenData(category.getChildren()));
        AttachEntity attachId = category.getAttach();
        if(Objects.nonNull(attachId)){
            categoryResponseDto.setAttach(AttachConvert.convertToAttachUrlDto(category.getAttach()));
        }
        return categoryResponseDto;
    }

    public CategoryDto fromNoTree(CategoryEntity category){

        CategoryDto categoryDto = category.getDto(false);
        AttachEntity attachId = category.getAttach();
        if(Objects.nonNull(attachId)){
            categoryDto.setAttach(AttachConvert.convertToAttachUrlDto(attachId));
        }

        return categoryDto;
    }
    public CategoryDto fromNoChild(CategoryEntity category){

        CategoryDto categoryDto = category.getDto(false);
        AttachEntity attachId = category.getAttach();
        if(Objects.nonNull(attachId)){
            categoryDto.setAttach(AttachConvert.convertToAttachUrlDto(attachId));
        }

        return categoryDto;
    }

    public CategoryResponseDto fromOpenDataNoChild(CategoryEntity category){

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setNameEn(category.getNameEn());
        categoryResponseDto.setNameUz(category.getNameUz());
        categoryResponseDto.setNameRu(category.getNameRu());
        categoryResponseDto.setParentId(category.getParentId());
        AttachEntity attachId = category.getAttach();
        if(Objects.nonNull(attachId)){
            categoryResponseDto.setAttach(AttachConvert.convertToAttachUrlDto(category.getAttach()));
        }
        return categoryResponseDto;

    }

    public CategoryDto generatorCategoryDto(String router){
        if(Objects.nonNull(router)){
            CategoryDto dto = new CategoryDto();
            dto.setRouter(router);
            return dto;
        }
        return null;
    }

    public List<CategoryResponseDto> fromOpenData(List<CategoryEntity> categoryList){
        return categoryList.stream().map(CategoryConvert::fromOpenData)
                .toList();
    }

    public List<CategoryDto> fromTree(List<CategoryEntity> categoryList){
        return categoryList.stream().map(CategoryConvert::fromTree)
                .filter(p -> p.getStatus() != EntityStatus.DELETED).toList();
    }

    public List<CategoryDto> fromTreeThreeLevel(List<CategoryEntity> categoryList){
        return categoryList.stream().map((category)->{
            CategoryDto dto = fromNoTree(category);
            dto.setChildren(category.getChildren().stream().map(CategoryConvert::fromOneLevelChild).toList());
            return dto; })
                .filter(p -> p.getStatus() != EntityStatus.DELETED).toList();
    }

    public List<CategoryResponseDto> fromOpenDataNoChild(List<CategoryEntity> categoryList){
        return categoryList.stream().map(CategoryConvert::fromOpenDataNoChild).toList();
    }

    public List<CategoryDto> fromNoTree(List<CategoryEntity> categoryList){
        return categoryList.stream().map(CategoryConvert::fromNoTree).toList();
    }

}
