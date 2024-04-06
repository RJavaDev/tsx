package uz.tsx.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass
public class BaseForParentAndChild extends BaseEntity {
    // TODO DELETE UNIQUE

    @Column(name = "name_uz", unique = true, nullable = false)
    private String nameUz;

    @Column(name = "name_ru", unique = true, nullable = false)
    private String nameRu;

    @Column(name = "name_en", unique = true, nullable = false)
    private String nameEn;

    @Column(name = "parentId")
    private Long parentId;

}
