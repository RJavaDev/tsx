package uz.tsx.service;

import uz.tsx.dto.announcement.additionInfo.AdditionComboValueDto;
import uz.tsx.entity.announcement.additionInfo.AdditionGroupEntity;

import java.util.List;

public interface AnnounceAdditionGroupService extends BaseInterface<AdditionGroupEntity> {

    boolean add(AdditionGroupEntity entity);

    List<AdditionComboValueDto> listAnnounceAdditionGroup(Long groupId);

    boolean update(AdditionGroupEntity entity);
}
