package uz.tsx.entity.chat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.constants.TableNames;
import uz.tsx.dto.chat.ChatRoomDto;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.entity.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = TableNames.CHAT_ROOM)
public class ChatRoomEntity extends BaseEntity {
    @Column(name = "announcement_id", nullable = false)
    private Long announcementId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_id", insertable = false, updatable = false)
    private AnnouncementEntity announcement;

    public ChatRoomDto toDto() {
        ChatRoomDto dto = new ChatRoomDto();
        toDto(this, dto);

        if(getAnnouncement() != null) {
            dto.setAnnouncement(getAnnouncement().toDto("additionalInfos", "additionalOptions"));
        }
        return dto;
    }
}
