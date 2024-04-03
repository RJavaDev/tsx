package uz.tsx.service;


import uz.tsx.dto.request.AdditionGroupUpdate;
import uz.tsx.entity.announcement.additionInfo.AdditionGroupEntity;

public interface AdditionGroupService extends BaseInterface<AdditionGroupEntity>{

    boolean add(AdditionGroupEntity entity);

    void update(AdditionGroupUpdate dto);
}
