package uz.tsx.dto.chat;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.UserDto;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.entity.chat.ChatStatus;

@Getter
@Setter
public class ChatUserDto extends BaseDto {
    private UserDto user;

    private ChatRoomDto chatRoom;

    private ChatStatus chatStatus;
}
