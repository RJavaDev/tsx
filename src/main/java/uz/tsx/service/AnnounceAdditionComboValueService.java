package uz.tsx.service;

import uz.tsx.entity.announcement.additionInfo.AdditionComboValueEntity;

public interface AnnounceAdditionComboValueService extends BaseInterface<AdditionComboValueEntity>{

    boolean add(AdditionComboValueEntity addedEntity);

    boolean update(AdditionComboValueEntity updateEntity);
}
