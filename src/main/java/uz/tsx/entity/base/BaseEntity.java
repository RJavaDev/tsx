package uz.tsx.entity.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
//import org.hibernate.annotations.TypeDef;
//import org.hibernate.annotations.TypeDefs;
import org.springframework.beans.BeanUtils;
import uz.tsx.constants.EntityStatus;
import uz.tsx.dto.base.BaseDto;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", length = 32, columnDefinition = "varchar(32) default 'CREATED'")
    @Enumerated(value = EnumType.STRING)
    private EntityStatus status = EntityStatus.CREATED;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    @Column(name = "createdBy")
    private Long createdBy;

    @Column(name = "modifiedBy")
    private Long modifiedBy;

    public void forCreate() {
        forCreate(null);
    }

    public void forCreate(Long creatorId) {
        this.setCreatedBy(creatorId);
        this.setCreatedDate(LocalDateTime.now());
        this.setStatus(EntityStatus.CREATED);
    }

    public void forUpdate() {
        forUpdate(null);
    }

    public void forUpdate(Long modifierId) {
        this.setModifiedBy(modifierId);
        this.setUpdatedDate(LocalDateTime.now());
        this.setStatus(EntityStatus.UPDATED);
    }

    public <DTO extends BaseDto, ENTITY extends BaseEntity> DTO toDto(ENTITY entity, DTO dto, String... ignoreProperties) {
        BeanUtils.copyProperties(entity, dto, ignoreProperties);
        return dto;
    }
}
