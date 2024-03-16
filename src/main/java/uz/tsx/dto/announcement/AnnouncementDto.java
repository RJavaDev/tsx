package uz.tsx.dto.announcement;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.CategoryDto;
import uz.tsx.dto.announcement.selector.AnnouncementInfoSelector;
import uz.tsx.dto.base.BaseDto;
import java.util.Set;

@Getter
@Setter
public class AnnouncementDto extends BaseDto {
    private String title;

    private Long categoryId;

    private CategoryDto category;

    private String attachPhotosUrl;

    private String description;

    private Long priceTagId;

    private AnnouncementPriceDto priceTag;

    private Long contactInfoId;

    private AnnouncementContactDto contactInfo;

    private Set<AnnouncementInfoSelector> additionalInfos;
//    private Set<AnnounceAdditionInfoDto> additionalInfos;

    private Set<AnnounceOptionDto> additionalOptions;
}
