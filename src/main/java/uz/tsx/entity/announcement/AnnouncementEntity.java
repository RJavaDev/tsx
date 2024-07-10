package uz.tsx.entity.announcement;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.annotations.Type;
import org.springframework.beans.BeanUtils;
import uz.tsx.constants.TableNames;
import uz.tsx.dto.announcement.additionInfo.AnnounceAdditionInfoDto;
import uz.tsx.dto.announcement.option.AnnounceOptionDto;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.announcement.selector.AnnounceOptionSelector;
import uz.tsx.dto.announcement.selector.AnnouncementInfoSelector;
import uz.tsx.entity.AttachEntity;
import uz.tsx.entity.CategoryEntity;
import uz.tsx.entity.UserEntity;
import uz.tsx.entity.base.BaseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = TableNames.ANNOUNCEMENT)
public class AnnouncementEntity extends BaseEntity {
    private String title;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity UserEntity;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "announcement_attach",
            joinColumns = {@JoinColumn(name = "announcement_id")},
            inverseJoinColumns = {@JoinColumn(name = "attach_id")})
    private List<AttachEntity> attachPhotos;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "isActive", columnDefinition = "boolean default true")
    private Boolean isActive;

    @Column(name = "i_saw")
    private Integer iSaw;

    @Column(name = "price_tag_id")
    private Long priceTagId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_tag_id", insertable = false, updatable = false)
    private AnnouncementPriceEntity priceTag;

    @Column(name = "contact_info_id")
    private Long contactInfoId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_info_id", insertable = false, updatable = false)
    private AnnouncementContactEntity contactInfo;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb", name = "additional_infos")
    private Set<AnnounceAdditionInfoDto> additionalInfos;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb", name = "additional_options")
    private Set<AnnounceOptionDto> additionalOptions;

    public AnnouncementDto toDto(String... ignoreProperties) {
        AnnouncementDto dto = new AnnouncementDto();
        super.toDto(this, dto, ignoreProperties);

        if(!ArrayUtils.contains(ignoreProperties, "additionalInfos") && getAdditionalInfos() != null) {
            Set<AnnouncementInfoSelector> additionalInfos = new HashSet<>();

            for(AnnounceAdditionInfoDto infoDto : getAdditionalInfos()) {
                AnnouncementInfoSelector sInfoDto = new AnnouncementInfoSelector();
                BeanUtils.copyProperties(infoDto, sInfoDto);
                additionalInfos.add(sInfoDto);
            }

            dto.setAdditionalInfos(additionalInfos);
        }

        if(!ArrayUtils.contains(ignoreProperties, "additionalOptions") && getAdditionalOptions() != null) {
            Set<AnnounceOptionSelector> additionalOptions = new HashSet<>();

            for(AnnounceOptionDto optionDto : getAdditionalOptions()) {
                AnnounceOptionSelector sOptionDto = new AnnounceOptionSelector();
                BeanUtils.copyProperties(optionDto, sOptionDto);
                additionalOptions.add(sOptionDto);
            }

            dto.setAdditionalOptions(additionalOptions);
        }

        return dto;
    }
}
