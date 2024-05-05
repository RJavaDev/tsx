package uz.tsx.entity.announcement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.constants.TableNames;
import uz.tsx.entity.RegionEntity;
import uz.tsx.entity.base.BaseEntity;

@Entity
@Getter
@Setter
@Table(name = TableNames.ANNOUNCEMENT_CONTACT)
public class AnnouncementContactEntity extends BaseEntity {
    private Double longitude;

    private Double latitude;

    private String phone;

    private String gmail;

    private String address;

    @Column(name = "regionId")
    private Long regionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regionId", insertable = false, updatable = false)
    private RegionEntity region;
}
