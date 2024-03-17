package uz.tsx.dto.announcement;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.CategoryDto;
import uz.tsx.dto.announcement.selector.AnnounceOptionSelector;
import uz.tsx.dto.announcement.selector.AnnouncementInfoSelector;
import uz.tsx.dto.base.BaseDto;

import java.util.*;

@Getter
@Setter
public class AnnouncementDto extends BaseDto {
    private String title;

    private Long categoryId;

    private CategoryDto category;

    private List<String> attachPhotosUrl;

    private List<String> attachMiniPhotosUrl;

    private String description;

    private Long priceTagId;

    private AnnouncementPriceDto priceTag;

    private Long contactInfoId;

    private AnnouncementContactDto contactInfo;

    private Set<AnnouncementInfoSelector> additionalInfos;

    private Set<AnnounceOptionSelector> additionalOptions;

    private Map<Long, List<Long>> selectedOptionsByGroup;               // 1 -> [2, 3, 4]    1 - groupId, [2,3,4] - optionIds     ## 0 mean NO-GROUP options

    public Map<Long, List<Long>> getSelectedOptionsByGroup() {
        if(additionalOptions == null || additionalOptions.isEmpty())
            return null;
        Map<Long, List<Long>> map = new HashMap<>();

        for(AnnounceOptionSelector selector : additionalOptions) {
            Long groupId = selector.getOptionGroupId() == null ? 0 : selector.getOptionGroupId();
            if(!map.containsKey(groupId))
                map.put(groupId, new ArrayList<>());

            List<Long> optionIds = map.get(groupId);
            optionIds.add(selector.getOptionId());
        }

        return map;
    }
}
