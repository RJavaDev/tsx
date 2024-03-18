package uz.tsx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.constants.TableNames;
import uz.tsx.entity.base.BaseEntity;

@Entity
@Getter
@Setter
@Table(name = TableNames.CURRENCY)
public class CurrencyEntity extends BaseEntity {
    private String name;    // O'zbek so'mi, USA dollar

    private String code;   // uzs, usd
}
