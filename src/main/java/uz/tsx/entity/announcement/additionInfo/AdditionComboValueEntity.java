package uz.tsx.entity.announcement.additionInfo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.entity.base.BaseEntity;

@Entity
@Getter
@Setter
@Table(name = "tsx_add_value")
public class AdditionComboValueEntity extends BaseEntity {
    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "group_id")
    private Long groupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private AdditionGroupEntity group;
}
