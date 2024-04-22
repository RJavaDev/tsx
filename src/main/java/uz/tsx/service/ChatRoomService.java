package uz.tsx.service;

import uz.tsx.dto.chat.ChatRoomDto;

public interface ChatRoomService {
    ChatRoomDto createOrGet(Long announceId);
}
