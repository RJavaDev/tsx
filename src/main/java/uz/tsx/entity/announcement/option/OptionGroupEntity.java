package uz.tsx.entity.announcement.option;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.constants.TableNames;
import uz.tsx.dto.CategoryDto;
import uz.tsx.dto.announcement.option.OptionGroupDto;
import uz.tsx.entity.base.BaseEntity;

@Entity
@Getter
@Setter
@Table(name = TableNames.OPTION_GROUP)
public class OptionGroupEntity extends BaseEntity {

    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "name_eu")
    private String nameEn;

    public OptionGroupDto toDto(String... ignoreProperties){
        return toDto(this, new OptionGroupDto(), ignoreProperties);
    }

}
