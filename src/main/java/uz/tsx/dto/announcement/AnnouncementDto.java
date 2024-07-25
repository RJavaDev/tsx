package uz.tsx.dto.announcement;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.CategoryDto;
import uz.tsx.dto.announcement.selector.AnnounceOptionSelector;
import uz.tsx.dto.announcement.selector.AnnouncementInfoSelector;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.dto.response.AttachUrlResponse;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
public class AnnouncementDto extends BaseDto {
    private String title;

    private Long categoryId;

    private CategoryDto category;

    private List<String> attachPhotosUrl = new ArrayList<>();

    private List<String> attachMiniPhotosUrl = new ArrayList<>();

    private List<AttachUrlResponse> attachUrlResponses;

    private String description;

    private Long priceTagId;

    private AnnouncementPriceDto priceTag;

    private Long contactInfoId;

    private AnnouncementContactDto contactInfo;

    private Integer iSaw;

    private Set<AnnouncementInfoSelector> additionalInfos;

    private Set<AnnounceOptionSelector> additionalOptions;

    private Map<Long, List<Long>> selectedOptionsByGroup;               // 1 -> [2, 3, 4]    1 - groupId, [2,3,4] - optionIds     ## 0 mean NO-GROUP options

    private LocalDateTime createDateTime;

    public LocalDateTime getCreateDateTime(){
        return this.getCreatedDate();
    }

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
