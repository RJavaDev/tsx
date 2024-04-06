package uz.tsx.entity.announcement.additionInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.constants.TableNames;
import uz.tsx.dto.announcement.additionInfo.AdditionGroupDto;
import uz.tsx.entity.CategoryEntity;
import uz.tsx.entity.base.BaseEntity;

@Entity
@Getter
@Setter
@Table(name = TableNames.ADDITION_GROUP)
public class AdditionGroupEntity extends BaseEntity {
    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "name_en")
    private String nameEn;

    private AdditionType type;

    @Column(name = "category_id")
    private Long categoryId;

    // TODO ManyToMany qilish kerak

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @JsonIgnore
    public AdditionGroupDto toDto(String... ignoreProperties){
        return toDto(this, new AdditionGroupDto(), ignoreProperties);
    }

}
