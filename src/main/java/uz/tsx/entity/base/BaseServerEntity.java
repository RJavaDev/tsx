package uz.tsx.entity.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.tsx.constants.EntityStatus;
import uz.tsx.dto.base.BaseServerDto;

@Getter
@Setter
@MappedSuperclass
public class BaseServerEntity extends BaseObject{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status", length = 32, columnDefinition = "varchar(32) default 'CREATED'")
    @Enumerated(value = EnumType.STRING)
    private EntityStatus status = EntityStatus.CREATED;

    /**********************************
     ***    convert ENTITY to DTO   ***
     **********************************/
    public <ENTITY extends BaseServerEntity, DTO extends BaseServerDto> DTO entityToDto(ENTITY entity, DTO dto) {
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public Integer getUniqueId() {
        return getId();
    }
}
