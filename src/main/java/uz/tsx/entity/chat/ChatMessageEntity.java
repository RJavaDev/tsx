package uz.tsx.entity.chat;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uz.tsx.constants.TableNames;
import uz.tsx.dto.chat.ChatMessageDto;
import uz.tsx.entity.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = TableNames.CHAT)
public class ChatMessageEntity extends BaseEntity {
    @Column(name = "sender_id", nullable = false)
    private Long senderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", insertable = false, updatable = false)
    private ChatUserEntity sender;

    @Column(name = "recipient_id", nullable = false)
    private Long recipientId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", insertable = false, updatable = false)
    private ChatUserEntity recipient;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Type(StringArrayType.class)
    @Column(name = "file_paths", columnDefinition = "text[]")
    private String[] filePaths;

    @Column(name = "is_read", columnDefinition = "boolean default false")
    private boolean isRead = false;

    public ChatMessageDto toDto() {
        ChatMessageDto dto = toDto(this, new ChatMessageDto());

        if(getSender() != null) {
            dto.setSender(getSender().toDto());
        }

        if(getRecipient() != null) {
            dto.setRecipient(getRecipient().toDto());
        }

        return dto;
    }
}
