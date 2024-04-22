package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.dto.UserDto;
import uz.tsx.dto.chat.ChatUserDto;
import uz.tsx.entity.chat.ChatRoomEntity;
import uz.tsx.entity.chat.ChatStatus;
import uz.tsx.entity.chat.ChatUserEntity;
import uz.tsx.repository.ChatUserRepository;
import uz.tsx.service.ChatRoomService;
import uz.tsx.service.ChatUserService;
import uz.tsx.validation.Validation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatUserServiceImpl implements ChatUserService {

    private final ChatUserRepository chatUserRepository;

    private final ChatRoomService chatRoomService;

    @Override
    public ChatUserDto createOrGet(ChatUserDto dto) {
        if(dto == null || dto.getUser() == null)
            throw new IllegalStateException("Bad request!");

        Optional<ChatUserEntity> chatUserOpt = chatUserRepository.getChatUserByUserIdAndRoomId(dto.getUser().getId(), dto.getChatRoom().getId());
        if(chatUserOpt.isEmpty()) {
            ChatUserEntity entity = new ChatUserEntity();
            BeanUtils.copyProperties(dto, entity);
            entity.setUser(dto.getUser().toEntity());
            entity.setChatRoom(dto.getChatRoom().toEntity(dto.getChatRoom(), new ChatRoomEntity()));
            entity.setUserId(dto.getUser().getId());
            entity.setChatRoomId(dto.getChatRoom().getId());
            chatUserRepository.save(entity);
            return entity.toDto();
        }

        chatUserOpt.get().setChatStatus(ChatStatus.ONLINE);
        chatUserRepository.save(chatUserOpt.get());

        return chatUserOpt.get().toDto(chatUserOpt.get(), new ChatUserDto(), "user", "chatRoom");
    }

    @Override
    public ChatUserDto get(Long userId, Long roomId) {
        if(!Validation.checkId(userId) || !Validation.checkId(roomId))
            throw new IllegalStateException("UserId or RoomId is not valid!");

        return chatUserRepository.getChatUserByUserIdAndRoomId(userId, roomId).map(ChatUserEntity::toDto).orElse(null);
    }

    @Override
    public void disconnect(ChatUserDto dto) {
        if(dto == null || !Validation.checkId(dto.getId()))
            throw new IllegalStateException("Bad request!");

        Optional<ChatUserEntity> chatUserOpt = chatUserRepository.findById(dto.getId());
        if(chatUserOpt.isPresent()) {
            chatUserOpt.get().setChatStatus(ChatStatus.OFFLINE);
            chatUserRepository.save(chatUserOpt.get());
        }
    }

    @Override
    public List<ChatUserDto> findConnectedUsers(Long chatRoomId) {
        return chatUserRepository.findAllByChatStatusAndRoomId(chatRoomId, ChatStatus.ONLINE).stream().map(ChatUserEntity::toDto).collect(Collectors.toList());
    }
}
