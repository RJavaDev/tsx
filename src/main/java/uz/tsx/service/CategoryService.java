package uz.tsx.service;

import uz.tsx.dto.CategoryDto;
import uz.tsx.entity.CategoryEntity;

import java.util.List;

public interface CategoryService extends BaseInterface<CategoryEntity>{

    boolean add(CategoryEntity category, String attachId);

    List<CategoryEntity> getAllTree();

    boolean update(CategoryEntity newUpdateObject, String attachId);

    CategoryDto findTreeFromBottom(Long childId);           // find category link to its parent and so on ...

    List<CategoryEntity> getChildCategoriesByParentId(Long parentId);

    List<CategoryEntity> getAllParentIsNull();
}
