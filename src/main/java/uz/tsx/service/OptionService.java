package uz.tsx.service;

import uz.tsx.dto.announcement.option.OptionDto;
import uz.tsx.entity.announcement.option.OptionEntity;

import java.util.List;

public interface OptionService extends BaseInterface<OptionEntity>{
    boolean add(OptionEntity entity);

    List<OptionEntity> findOptionsByGroupId(Long groupId);

}
