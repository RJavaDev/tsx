package uz.tsx.bot.serviceBot.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsx.bot.dto.CategoryDto;
import uz.tsx.entity.CategoryEntity;
import uz.tsx.repository.CategoryRepository;
import uz.tsx.service.impl.CategoryServiceImpl;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryBotServiceImpl {

    private final CategoryServiceImpl categoryService;
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getCategory() {
    return categoryRepository.getCategoryTree().stream().map(categoryEntity -> {
            CategoryDto categoryDto=new CategoryDto();
            categoryDto.setId(categoryEntity.getId());
            categoryDto.setName(categoryEntity.getNameUz());
            return categoryDto;
        }).toList();
    }


    public List<CategoryDto> getCategoryListById(Long categoryId) {
       return categoryRepository.getChilddListById(categoryId).stream().map(categoryEntity -> {
           CategoryDto categoryDto=new CategoryDto();
           categoryDto.setId(categoryEntity.getId());
           categoryDto.setName(categoryEntity.getNameUz());
           return categoryDto;
       }).toList();

    }
}
