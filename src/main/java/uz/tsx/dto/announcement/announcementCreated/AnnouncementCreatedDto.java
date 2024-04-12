package uz.tsx.dto.announcement.announcementCreated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.announcement.selector.AnnounceOptionSelector;
import uz.tsx.dto.announcement.selector.AnnouncementInfoSelector;

import java.util.Set;

@Getter
@Setter
public class AnnouncementCreatedDto {

    @NotBlank(message = "title cannot be empty")
    private String title;

    @NotNull(message = "categoryId cannot be empty")
    private Long categoryId;

    @NotBlank(message = "description cannot be empty")
    private String description;

    private AnnouncementPriceCreateDto priceTag;

    private AnnouncementContactCreateDto contactInfo;

    private Set<AnnouncementInfoSelector> additionalInfos;

    private Set<AnnounceOptionSelector> additionalOptions;

}
