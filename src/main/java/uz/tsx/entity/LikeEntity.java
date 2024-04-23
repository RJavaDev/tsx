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

   public enum LikeStatus{
        DISLIKE,
        LIKE

    }

    @ManyToOne
    private AnnouncementEntity announcement;

    @Column(name = "count")
    private Integer likeCount;

    @Column(name = "like_status")
    private LikeStatus likeStatus;


}
