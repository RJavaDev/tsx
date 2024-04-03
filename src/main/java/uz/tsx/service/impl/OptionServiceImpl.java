package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.dto.announcement.option.OptionDto;
import uz.tsx.entity.announcement.option.OptionEntity;
import uz.tsx.repository.OptionRepository;
import uz.tsx.service.OptionService;
import uz.tsx.validation.CommonSchemaValidator;
import uz.tsx.validation.Validation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionRepository repository;

    private final CommonSchemaValidator schemaValidator;

    @Override
    public boolean add(OptionEntity entity) {

        schemaValidator.validateCategory(entity.getCategoryId());
        schemaValidator.validateOptionGroupId(entity.getOptionGroupId());

        entity.forCreate(SecurityUtils.getUserId());

        repository.save(entity);

        return Boolean.TRUE;
    }

    @Override
    public OptionEntity getById(Long id) {
        return schemaValidator.validateOption(id);
    }

    @Override
    public List<OptionEntity> getAll() {
        return repository.getAllOptions();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        schemaValidator.validateID(id);
        repository.delete(id);
    }


    @Override
    public List<OptionEntity> findOptionsByGroupId(Long groupId) {
        if(!Validation.checkId(groupId)) return null;

        return repository.findAllOptionsByGroupId(groupId);
    }
}
