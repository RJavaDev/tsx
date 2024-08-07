package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.constants.EntityStatus;
import uz.tsx.dto.CategoryDto;
import uz.tsx.entity.CategoryEntity;
import uz.tsx.exception.CategoryNotFoundException;
import uz.tsx.repository.CategoryRepository;
import uz.tsx.service.CategoryService;
import uz.tsx.validation.CommonSchemaValidator;
import uz.tsx.validation.Validation;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private final CommonSchemaValidator commonSchemaValidator;

    @Override
    public boolean add(CategoryEntity category, String attachId) {
        Long userId = SecurityUtils.getUserId();

        commonSchemaValidator.categoryParentAttachIdTogetherInspection(category, attachId);

        category.forCreate(userId);

        repository.save(category);
        return true;
    }

    @Override
    public CategoryEntity getById(Long id) {
        return commonSchemaValidator.validateGetCategory(id);
    }

    @Override
    public List<CategoryEntity> getAll() {
        return repository.findAllCategory();
    }

    @Override
    public List<CategoryEntity> getAllTree() {
        return repository.getCategoryTree();
    }


    @Override
    public boolean update(CategoryEntity newUpdateObject, String attachId) {

        CategoryEntity updateEntity = commonSchemaValidator.validateCategoryUpdate(newUpdateObject);

        commonSchemaValidator.categoryUpdateParentAttachIdTogetherInspection(updateEntity, attachId);

        updateEntity.forUpdate(SecurityUtils.getUserId());
        repository.save(updateEntity);
        return true;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        commonSchemaValidator.doesCategoryExist(id);
        repository.categoryDelete(id);
    }

    @Override
    public CategoryDto findTreeFromBottom(Long childId) {
        if(!Validation.checkId(childId)) return null;

        List<CategoryEntity> cEntities = repository.getCategoriesByChildToAncestors(childId);
        CategoryDto dto = new CategoryDto();
        CategoryDto temp = dto;

        for(CategoryEntity entity : cEntities) {
            entity.toDto(entity, temp);
            if(entity.getParentId() != null) {
                temp.setParent(new CategoryDto());
                temp = temp.getParent();
            }
        }

        return dto;
    }

    @Override
    public List<CategoryEntity> getAllParentIsNull() {
        return repository.getAllParentIsNull();
    }


    private CategoryEntity childIdAndParentIdVerify(CategoryEntity category, Long categoryId) {



        CategoryEntity entity = null;
        if (category.getParentId() != null) {

            entity = parentIdVerify(category, categoryId);

        } else {
            entity = repository.findByCategoryId(categoryId).orElseThrow(
                    () -> new CategoryNotFoundException(categoryId + " id not found!!!"));
        }
        return entity;
    }

    private CategoryEntity parentIdVerify(CategoryEntity category, Long categoryId) {

        CategoryEntity entity = null;
        List<CategoryEntity> parentAndChild = repository.getCategoryIdParentAndChild(categoryId, category.getParentId());

        if (parentAndChild.size() == 2) {
            for (CategoryEntity categoryDB : parentAndChild) {

                if (Objects.equals(categoryDB.getId(), categoryId)) {
                    entity = categoryDB;
                }

            }

        } else {
            throw new CategoryNotFoundException("id not found!!!");
        }
        return entity;
    }

    private void deletedObjectOriginDataBase(String updateObjectName) {
        CategoryEntity originDBObject = repository.findByCategoryName(updateObjectName);
        if (originDBObject.getStatus() == EntityStatus.DELETED) {
            repository.delete(originDBObject);
        } else {
            throw new CategoryNotFoundException(updateObjectName + " the category with this name already exists");
        }
    }

    @Override
    public List<CategoryEntity> getChildCategoriesByParentId(Long parentId) {
        return repository.getChildCategoriesByParentCategoryId(parentId);
    }

}
