package uz.tsx.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.tsx.constants.EntityStatus;
import uz.tsx.entity.base.BaseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private EntityStatus status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime updatedDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long createdBy;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long modifiedBy;

    public <DTO extends BaseDto, ENTITY extends BaseEntity> ENTITY toEntity(DTO dto, ENTITY entity, String... ignoreProperties){
        BeanUtils.copyProperties(dto, entity, ignoreProperties);
        return entity;
    }
}
