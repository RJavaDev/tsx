package uz.tsx.entity.announcement;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uz.tsx.constants.TableNames;
import uz.tsx.dto.AnnounceOptionDto;
import uz.tsx.entity.AttachEntity;
import uz.tsx.entity.CategoryEntity;
import uz.tsx.entity.base.BaseEntity;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = TableNames.ANNOUNCEMENT)
public class AnnouncementEntity extends BaseEntity {
    private String title;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

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

    @Column(name = "price_tag_id")
    private Integer priceTagId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_tag_id", insertable = false, updatable = false)
    private AnnouncementPriceEntity priceTag;

    @Column(name = "contact_info_id")
    private Integer contactInfoId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_info_id", insertable = false, updatable = false)
    private AnnouncementContactEntity contactInfo;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb", name = "additional_options")
    private Set<AnnounceOptionDto> additionalOptions;
}
