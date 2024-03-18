package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsx.dto.announcement.option.OptionDto;
import uz.tsx.entity.announcement.option.OptionEntity;
import uz.tsx.repository.OptionRepository;
import uz.tsx.service.OptionService;
import uz.tsx.validation.Validation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;

    @Override
    public OptionDto getById(Long id) {
        return null;
    }

    @Override
    public List<OptionDto> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<OptionDto> findOptionsByGroupId(Long groupId) {
        if(!Validation.checkId(groupId)) return null;

        List<OptionEntity> entities = optionRepository.findAllOptionsByGroupId(groupId);
        return entities.stream().map(entity -> entity.toDto(entity, new OptionDto())).toList();
    }
}
