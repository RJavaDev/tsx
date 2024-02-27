package uz.tsx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.constants.TableNames;
import uz.tsx.dto.RegionDto;
import uz.tsx.entity.base.BaseForParentAndChild;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = TableNames.REGION)
public class RegionEntity extends BaseForParentAndChild {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    List<RegionEntity> children = new ArrayList<>();

    @JsonIgnore
    public RegionDto toDto(String... ignoreProperties){
        return toDto(this, new RegionDto(), ignoreProperties);
    }
}
