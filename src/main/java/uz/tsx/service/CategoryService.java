package uz.tsx.service;

import uz.tsx.entity.CategoryEntity;

import java.util.List;

public interface CategoryService extends BaseInterface<CategoryEntity>{

    boolean add(CategoryEntity category, String attachId);

    List<CategoryEntity> getAllTree();

    boolean update(CategoryEntity newUpdateObject, Integer categoryId, String attachId);
}
