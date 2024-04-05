package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.dto.announcement.additionInfo.AdditionComboValueDto;
import uz.tsx.entity.announcement.additionInfo.AdditionComboValueEntity;
import uz.tsx.entity.announcement.additionInfo.AdditionGroupEntity;
import uz.tsx.repository.AnnounceAdditionGroupRepository;
import uz.tsx.service.AnnounceAdditionGroupService;
import uz.tsx.validation.CommonSchemaValidator;
import uz.tsx.validation.Validation;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnounceAdditionGroupServiceImpl implements AnnounceAdditionGroupService {

    private final AnnounceAdditionGroupRepository repository;
    private final CommonSchemaValidator schemaValidator;

    @Override
    public boolean add(AdditionGroupEntity entity) {

        schemaValidator.validateCategory(entity.getCategoryId());
        entity.forCreate(SecurityUtils.getUserId());

        repository.save(entity);

        return true;

    }

    @Override
    public List<AdditionComboValueDto> listAnnounceAdditionGroup(Long groupId) {
        if(!Validation.checkId(groupId))
            throw new IllegalStateException("Group is not valid!");

        List<AdditionComboValueEntity> comboValues = repository.getAdditionGroupComboValues(groupId);
        return comboValues.stream().map(AdditionComboValueEntity::toDto).collect(Collectors.toList());
    }

    @Override
    public boolean update(AdditionGroupEntity entity) {

        AdditionGroupEntity updateEntity = schemaValidator.validateAdditionGroupUpdate(entity);

        entity.forUpdate(SecurityUtils.getUserId());

        repository.save(updateEntity);

        return true;
    }

    @Override
    public AdditionGroupEntity getById(Long id) {
        return schemaValidator.validateAdditionGroup(id);
    }

    @Override
    public List<AdditionGroupEntity> getAll() {
        return repository.getAllAdditionGroup();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        schemaValidator.validateAdditionGroupId(id);
        repository.delete(id);
    }
}
