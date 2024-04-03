package uz.tsx.entity.announcement.option;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.constants.TableNames;
import uz.tsx.dto.RegionDto;
import uz.tsx.dto.announcement.option.OptionDto;
import uz.tsx.entity.CategoryEntity;
import uz.tsx.entity.base.BaseEntity;

@Entity
@Getter
@Setter
@Table(name = TableNames.OPTION)
public class OptionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "name_eu")
    private String nameEn;

    @Column(name = "group_id")
    private Long optionGroupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private OptionGroupEntity optionGroup;

    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @JsonIgnore
    public OptionDto toDto(String... ignoreProperties) {
        return toDto(this, new OptionDto(), ignoreProperties);
    }

}
