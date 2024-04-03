package uz.tsx.service;

import uz.tsx.entity.announcement.option.OptionGroupEntity;

public interface OptionGroupService extends BaseInterface<OptionGroupEntity>{

    OptionGroupEntity add(OptionGroupEntity entity);

    boolean update(OptionGroupEntity entity);
}
