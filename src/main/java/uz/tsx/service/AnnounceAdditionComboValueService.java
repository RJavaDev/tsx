package uz.tsx.service;

import uz.tsx.entity.announcement.additionInfo.AdditionComboValueEntity;

import java.util.List;

public interface AnnounceAdditionComboValueService extends BaseInterface<AdditionComboValueEntity>{

    boolean add(AdditionComboValueEntity addedEntity);

    boolean update(AdditionComboValueEntity updateEntity);

    List<AdditionComboValueEntity> getComboValueByGroupId(Long id);
}
