package uz.tsx.dto.base;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import uz.tsx.entity.base.BaseEntity;


@Data
public class BaseUserDto {

    public <DTO extends BaseUserDto, ENTITY extends BaseEntity> ENTITY toEntity(DTO dto, ENTITY entity, String... ignoreProperties){
        BeanUtils.copyProperties(dto, entity, ignoreProperties);
        return entity;
    }
}
