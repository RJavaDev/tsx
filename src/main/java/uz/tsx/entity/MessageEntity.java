package uz.tsx.entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uz.tsx.constants.TableNames;
import uz.tsx.entity.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = TableNames.MESSAGE)
public class MessageEntity extends BaseEntity {
    @Column(name = "from_user_id", nullable = false)
    private Integer fromUserId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id", insertable = false, updatable = false)
    private UserEntity fromUser;

    @Column(name = "to_user_id", nullable = false)
    private Integer toUserId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id", insertable = false, updatable = false)
    private UserEntity toUser;

    @Column(name = "text", columnDefinition = "text")
    private String text;

    @Type(StringArrayType.class)
    @Column(name = "file_paths", columnDefinition = "text[]")
    private String[] filePaths;

    @Column(name = "is_read", columnDefinition = "boolean default false")
    private boolean isRead = false;
}
