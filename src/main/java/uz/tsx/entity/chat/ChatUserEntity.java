package uz.tsx.entity.chat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.constants.TableNames;
import uz.tsx.dto.UserDto;
import uz.tsx.dto.chat.ChatUserDto;
import uz.tsx.entity.UserEntity;
import uz.tsx.entity.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = TableNames.CHAT_USER)
public class ChatUserEntity extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "chat_room_id", nullable = false)
    private Long chatRoomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", insertable = false, updatable = false)
    private ChatRoomEntity chatRoom;

    @Column(name = "chat_status")
    @Enumerated(EnumType.STRING)
    private ChatStatus chatStatus;

    @Transactional
    public ChatUserDto toDto(String... ignoreProperties) {
        ChatUserDto dto = new ChatUserDto();
        toDto(this, dto, ignoreProperties);

        if(getUser() != null) {
            dto.setUser(getUser().toDto());
        }

        if(getChatRoom() != null) {
            dto.setChatRoom(getChatRoom().toDto());
        }

        return dto;
    }
}
