package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.entity.announcement.additionInfo.AdditionComboValueEntity;
import uz.tsx.repository.AnnounceAdditionComboValueRepository;
import uz.tsx.service.AnnounceAdditionComboValueService;
import uz.tsx.validation.CommonSchemaValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnounceAdditionComboValueServiceImpl implements AnnounceAdditionComboValueService {

    private final AnnounceAdditionComboValueRepository repository;

    private final CommonSchemaValidator schemaValidator;

    @Override
    public boolean add(AdditionComboValueEntity addedEntity) {

        schemaValidator.validateAdditionGroupId(addedEntity.getGroupId());
        repository.save(addedEntity);

        return true;
    }

    @Override
    public boolean update(AdditionComboValueEntity updateEntity) {

        AdditionComboValueEntity updatedOriginEntity = schemaValidator.validateAdditionComboValue(updateEntity);

        repository.save(updatedOriginEntity);

        return true;
    }

    @Override
    public AdditionComboValueEntity getById(Long id) {
        return schemaValidator.validateAdditionComboValue(id);
    }

    @Override
    public List<AdditionComboValueEntity> getAll() {
        return repository.getAllAdditionComboValue();
    }

    @Override
    @Transactional
    public void delete(Long id) {

        schemaValidator.validateAdditionComboValueId(id);
        repository.delete(id);
    }
}
