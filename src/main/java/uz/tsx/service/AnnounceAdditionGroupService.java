package uz.tsx.service;

import uz.tsx.dto.announcement.additionInfo.AdditionComboValueDto;

import java.util.List;

public interface AnnounceAdditionGroupService {
    List<AdditionComboValueDto> listAnnounceAdditionGroup(Long groupId);
}
