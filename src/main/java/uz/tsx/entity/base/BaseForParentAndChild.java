package uz.tsx.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass
public class BaseForParentAndChild extends BaseServerModifierEntity{

    @Column(unique = true, nullable = false)
    private String name_uz;
    @Column(unique = true, nullable = false)
    private String name_ru;
    @Column(unique = true, nullable = false)
    private String name_en;

    @Column(name = "parentId")
    private Long parentId;


}
