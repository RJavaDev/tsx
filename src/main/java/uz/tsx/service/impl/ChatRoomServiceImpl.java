package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsx.dto.chat.ChatRoomDto;
import uz.tsx.entity.chat.ChatRoomEntity;
import uz.tsx.repository.ChatRoomRepository;
import uz.tsx.service.ChatRoomService;
import uz.tsx.validation.Validation;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoomDto createOrGet(Long announceId) {
        if(!Validation.checkId(announceId))
            throw new IllegalStateException("announceId is not valid!");

        Optional<ChatRoomEntity> chatRoomOpt = chatRoomRepository.getChatRoomByAnnounceId(announceId);
        if(chatRoomOpt.isEmpty()) {
            ChatRoomEntity newChatRoom = new ChatRoomEntity();
            newChatRoom.setAnnouncementId(announceId);
            chatRoomRepository.save(newChatRoom);
            return newChatRoom.toDto();
        }

        return chatRoomOpt.get().toDto();
    }
}
