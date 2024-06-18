package uz.tsx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.tsx.constants.EntityStatus;
import uz.tsx.constants.TableNames;
import uz.tsx.dto.CategoryDto;
import uz.tsx.entity.announcement.additionInfo.AdditionGroupEntity;
import uz.tsx.entity.base.BaseEntity;
import uz.tsx.entity.base.BaseForParentAndChild;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = TableNames.CATEGORY)
public class CategoryEntity extends BaseEntity {

    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "parentId")
    private Long parentId;

    @OneToOne(cascade = CascadeType.ALL)
    private AttachEntity attach;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    List<CategoryEntity> children = new ArrayList<>();

    private String router;
    @JsonIgnore
    public CategoryDto getDto(){
        return getDto(false);
    }

    @JsonIgnore
    public CategoryDto getDto(boolean withChildren) {
        CategoryDto dto = new CategoryDto();
        BeanUtils.copyProperties(this, dto);
        if (this.getChildren() != null && withChildren) {
            dto.setChildren(
                    this.getChildren().stream()
                            .map(CategoryEntity::getDto)
                            .filter(p -> p.getStatus() != EntityStatus.DELETED)
                            .collect(Collectors.toList()));
        }
        return dto;
    }

    public CategoryDto toDto(String... ignoreProperties){
        return toDto(this, new CategoryDto(), ignoreProperties);
    }
}
