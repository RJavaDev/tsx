package uz.tsx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.constants.TableNames;
import uz.tsx.entity.base.BaseForParentAndChild;
import uz.tsx.entity.base.BaseServerModifierEntity;

@Getter
@Setter
@Entity
@Table(name = TableNames.EMAIL)
public class EmailEntity extends BaseServerModifierEntity {
    private String email;
    private Integer cod;
}
