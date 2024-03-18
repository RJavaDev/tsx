package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsx.dto.announcement.additionInfo.AdditionComboValueDto;
import uz.tsx.entity.announcement.additionInfo.AdditionComboValueEntity;
import uz.tsx.repository.AnnounceAdditionGroupRepository;
import uz.tsx.service.AnnounceAdditionGroupService;
import uz.tsx.validation.Validation;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnounceAdditionGroupServiceImpl implements AnnounceAdditionGroupService {

    private final AnnounceAdditionGroupRepository announceAdditionGroupRepository;

    @Override
    public List<AdditionComboValueDto> listAnnounceAdditionGroup(Long groupId) {
        if(!Validation.checkId(groupId))
            throw new IllegalStateException("Group is not valid!");

        List<AdditionComboValueEntity> comboValues = announceAdditionGroupRepository.getAdditionGroupComboValues(groupId);
        return comboValues.stream().map(AdditionComboValueEntity::toDto).collect(Collectors.toList());
    }
}
