package uz.tsx.dto.announcement.announcementCreated;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.announcement.selector.AnnounceOptionSelector;
import uz.tsx.dto.announcement.selector.AnnouncementInfoSelector;

import java.util.Set;

@Getter
@Setter
public class AnnouncementCreatedDto {

    @NotNull(message = "")
    private String title;

    private Long categoryId;

    private String description;

    private AnnouncementPriceCreateDto priceTag;

    private AnnouncementContactCreateDto contactInfo;

    private Set<AnnouncementInfoSelector> additionalInfos;

    private Set<AnnounceOptionSelector> additionalOptions;

}
