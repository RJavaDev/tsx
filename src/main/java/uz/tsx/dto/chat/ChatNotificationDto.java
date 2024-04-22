package uz.tsx.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatNotificationDto {
    private Long id;

    private ChatUserDto sender;

    private ChatUserDto recipient;

    private String content;
}
