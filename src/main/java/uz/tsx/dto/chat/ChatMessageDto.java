package uz.tsx.dto.chat;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseDto;

@Getter
@Setter
public class ChatMessageDto extends BaseDto {
    private ChatUserDto sender;

    private ChatUserDto recipient;

    private String content;

    private String[] filePaths;

    private boolean isRead;
}
