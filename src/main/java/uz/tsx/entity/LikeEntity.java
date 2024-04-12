package uz.tsx.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.constants.TableNames;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.entity.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = TableNames.LIKE)
public class LikeEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private AnnouncementEntity announcement;

    @Column(name = "count")
    private Integer likeCount;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private UserEntity user;



}
