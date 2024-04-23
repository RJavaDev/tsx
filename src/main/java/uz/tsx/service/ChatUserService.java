package uz.tsx.service;

import uz.tsx.dto.chat.ChatUserDto;

import java.util.List;

public interface ChatUserService {
    ChatUserDto createOrGet(ChatUserDto dto);

    ChatUserDto get(Long userId, Long roomId);

    void disconnect(ChatUserDto dto);

    List<ChatUserDto> findConnectedUsers(Long chatRoomId);

}
