package uz.tsx.entity.announcement.option;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.constants.TableNames;

@Entity
@Getter
@Setter
@Table(name = TableNames.OPTION)
public class OptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name_uz;

    private String name_ru;

    private String name_en;

    @Column(name = "group_id")
    private Integer optionGroupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private OptionGroupEntity optionGroup;
}
