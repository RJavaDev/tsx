package uz.tsx.dto.chat;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.base.BaseDto;

@Getter
@Setter
public class ChatRoomDto extends BaseDto {
    private AnnouncementDto announcement;
}
