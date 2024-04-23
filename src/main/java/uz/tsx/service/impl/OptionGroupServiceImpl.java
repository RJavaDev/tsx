package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.entity.announcement.option.OptionGroupEntity;
import uz.tsx.repository.OptionGroupRepository;
import uz.tsx.service.OptionGroupService;
import uz.tsx.validation.CommonSchemaValidator;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OptionGroupServiceImpl implements OptionGroupService {

    private final OptionGroupRepository repository;

    private final CommonSchemaValidator schemaValidator;


    @Override
    public OptionGroupEntity add(OptionGroupEntity entity) {

        entity.forCreate(SecurityUtils.getUserId());

        return repository.save(entity);
    }

    @Override
    public boolean update(OptionGroupEntity entity) {
        schemaValidator.validateOptionGroupId(entity.getId());
        entity.forUpdate(SecurityUtils.getUserId());
        repository.save(entity);
        return true;
    }

    @Override
    public OptionGroupEntity getById(Long id) {
        return schemaValidator.validateOptionGroup(id);
    }

    @Override
    public List<OptionGroupEntity> getAll() {
        return repository.getAllOptionGroup();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }
}
