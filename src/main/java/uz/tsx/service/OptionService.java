package uz.tsx.service;

import uz.tsx.dto.announcement.option.OptionDto;

import java.util.List;

public interface OptionService extends BaseInterface<OptionDto>{
    List<OptionDto> findOptionsByGroupId(Long groupId);
}
