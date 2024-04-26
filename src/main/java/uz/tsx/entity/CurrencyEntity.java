package uz.tsx.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.constants.TableNames;
import uz.tsx.dto.CategoryDto;
import uz.tsx.dto.CurrencyDto;
import uz.tsx.entity.base.BaseEntity;

@Entity
@Getter
@Setter
@Table(name = TableNames.CURRENCY)
public class CurrencyEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;    // O'zbek so'mi, USA dollar

    @Column(nullable = false, unique = true)
    private String code;   // uzs, usd

    public CurrencyDto toDto(String... ignoreProperties){
        return toDto(this, new CurrencyDto(), ignoreProperties);
    }
}
