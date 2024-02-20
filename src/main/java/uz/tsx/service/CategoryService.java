package uz.tsx.service;

import uz.tsx.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    boolean addCategory(CategoryEntity category, String attachId);

    CategoryEntity getCategoryById(Integer id);

    CategoryEntity getCategoryByIdTree(Integer id);

    List<CategoryEntity> getAllCategory();

    List<CategoryEntity> getAllCategoryTree();

    boolean updateCategory(CategoryEntity newUpdateObject, Integer categoryId, String attachId);

    void delete(Integer id);
}
