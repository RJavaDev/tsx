package uz.tsx.entity.announcement.option;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.constants.TableNames;
import uz.tsx.entity.base.BaseEntity;

@Entity
@Getter
@Setter
@Table(name = TableNames.OPTION_GROUP)
public class OptionGroupEntity extends BaseEntity {
    private String name_uz;

    private String name_ru;

    private String name_en;
}
