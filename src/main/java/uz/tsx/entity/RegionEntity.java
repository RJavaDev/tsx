package uz.tsx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.tsx.constants.EntityStatus;
import uz.tsx.constants.TableNames;
import uz.tsx.dto.CategoryDto;
import uz.tsx.dto.RegionDto;
import uz.tsx.entity.base.BaseForParentAndChild;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = TableNames.REGION)
public class RegionEntity extends BaseForParentAndChild {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    List<RegionEntity> children = new ArrayList<>();

    @JsonIgnore
    public RegionDto getDto(){
        return getDto(false);
    }

    @JsonIgnore
    public RegionDto getDto(boolean withChildren) {
        RegionDto dto = new RegionDto();
        BeanUtils.copyProperties(this, dto);
        if (this.getChildren() != null && withChildren) {
            dto.setChildren(
                    this.getChildren().stream()
                            .map(RegionEntity::getDto)
                            .filter(p -> p.getStatus() != EntityStatus.DELETED)
                            .collect(Collectors.toList()));
        }
        return dto;
    }

    @JsonIgnore
    public RegionDto toDto(String... ignoreProperties) {
        return toDto(this, new RegionDto(), ignoreProperties);
    }

    public String languageFilterForBot(String lang){
        return switch (lang) {
            case "uz" -> super.getNameUz();
            case "ru" -> super.getNameRu();
            default -> super.getNameEn();
        };
    }
}
