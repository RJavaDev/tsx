package uz.tsx.entity.announcement.additionInfo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.entity.CategoryEntity;
import uz.tsx.entity.base.BaseEntity;

@Entity
@Getter
@Setter
@Table(name = "tsx_add_group")
public class AdditionGroupEntity extends BaseEntity {
    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "int_value")
    private Integer intValue;

    @Column(name = "float_value")
    private Float floatValue;

    @Column(name = "color_value")
    private String colorValue;

    @Column(name = "string_value")
    private String stringValue;

    private AdditionType type;

    @Column(name = "category_id")
    private Integer categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;
}
