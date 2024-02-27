package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.constants.EntityStatus;
import uz.tsx.entity.AttachEntity;
import uz.tsx.entity.CategoryEntity;
import uz.tsx.exception.CategoryNotFoundException;
import uz.tsx.repository.CategoryRepository;
import uz.tsx.service.CategoryService;
import uz.tsx.validation.CommonSchemaValidator;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private final CommonSchemaValidator commonSchemaValidator;

    @Override
    public boolean add(CategoryEntity category, String attachId) {
        Integer userId = SecurityUtils.getUserId();

        CategoryEntity getByCategoryNameOriginDB = repository.findByCategoryName(category.getName());

        commonSchemaValidator.categoryStatusCheck(getByCategoryNameOriginDB, category);
        AttachEntity attach = commonSchemaValidator.validateAttach(attachId);

        category.setAttach(attach);
        category.forCreate(userId);

        repository.save(category);
        return true;
    }

    @Override
    public CategoryEntity getById(Integer id) {
        if (id == null) return null;

        return repository.findByCategoryId(id).orElseThrow(
                () -> {
                    throw new CategoryNotFoundException(id + "-id not found!!!");
                }
        );
    }

    @Override
    public CategoryEntity getByIdTree(Integer id) {
        if (id == null) return null;
        return repository.findById(id).orElseThrow(
                () -> {
                    throw new CategoryNotFoundException(id + "-id not found");
                }
        );
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
    public boolean update(CategoryEntity newUpdateObject, Integer categoryId, String attachId) {
        CategoryEntity entity = childIdAndParentIdVerify(newUpdateObject, categoryId);
        entity.setParentId(newUpdateObject.getParentId());

        String updateObjectName = newUpdateObject.getName();
        if (!updateObjectName.isEmpty()) {
            deletedObjectOriginDataBase(updateObjectName);
            entity.setName(updateObjectName);
        }

        AttachEntity attach = commonSchemaValidator.validateAttach(attachId);
        entity.setAttach(attach);

        entity.forUpdate(SecurityUtils.getUserId());
        repository.save(entity);
        return true;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (id != null) {
            repository.findByCategoryId(id).orElseThrow(
                    () -> new CategoryNotFoundException(id + " id not found!!!"));
        }
        repository.categoryDelete(id);
    }



    private CategoryEntity childIdAndParentIdVerify(CategoryEntity category, Integer categoryId) {

        CategoryEntity entity = null;
        if (category.getParentId() != null) {

            entity = parentIdVerify(category, categoryId);

        } else {
            entity = repository.findByCategoryId(categoryId).orElseThrow(
                    () -> new CategoryNotFoundException(categoryId + " id not found!!!"));
        }
        return entity;
    }

    private CategoryEntity parentIdVerify(CategoryEntity category, Integer categoryId) {

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
}
