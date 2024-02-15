package uz.tsx.dto.base;

import lombok.*;
import org.springframework.beans.BeanUtils;
import uz.tsx.entity.base.BaseServerModifierEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseServerModifierDto extends BaseServerDto{
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Integer createdBy;

    private Integer modifiedBy;

    public <DTO extends BaseServerModifierDto, ENTITY extends BaseServerModifierEntity> ENTITY toEntity(DTO dto, ENTITY entity, String... ignoreProperties){
        BeanUtils.copyProperties(dto, entity, ignoreProperties);
        return entity;
    }
}
