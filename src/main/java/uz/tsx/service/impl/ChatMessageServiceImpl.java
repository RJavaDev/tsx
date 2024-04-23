package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.ValidationUtils;
import uz.tsx.dto.chat.ChatMessageDto;
import uz.tsx.entity.chat.ChatMessageEntity;
import uz.tsx.entity.chat.ChatUserEntity;
import uz.tsx.repository.ChatMessageRepository;
import uz.tsx.service.ChatMessageService;
import uz.tsx.validation.Validation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Override
    public ChatMessageDto save(ChatMessageDto dto) {
        if(dto == null || dto.getSender() == null || dto.getRecipient() == null)
            throw new IllegalStateException("Can't save message (user is not valid)!");

        if(!Objects.equals(dto.getSender().getChatRoom().getId(), dto.getRecipient().getChatRoom().getId()))
            throw new IllegalStateException("ChatRoom is not equal!");

        ChatMessageEntity entity = new ChatMessageEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.forCreate();
        entity.setSenderId(dto.getSender().getId());
        entity.setRecipientId(dto.getRecipient().getId());
        entity.setSender(dto.getSender().toEntity(dto.getSender(), new ChatUserEntity()));
        entity.setRecipient(dto.getRecipient().toEntity(dto.getRecipient(), new ChatUserEntity()));

        chatMessageRepository.save(entity);
        return entity.toDto();
    }

    @Override
    public List<ChatMessageDto> findChatMessages(Long senderId, Long recipientId) {
        if(!Validation.checkId(senderId) || !Validation.checkId(recipientId))
            throw new IllegalStateException("senderId or recipientId is not valid!");

        List<ChatMessageEntity> chatMessages = chatMessageRepository.getAllMessages(senderId, recipientId);
        return chatMessages.stream().map(ChatMessageEntity::toDto).collect(Collectors.toList());
    }


//    @Override
//    public List<ChatDto> getAllMessages(Long userId1, Long userId2) {
//        List<ChatEntity> writtenMessagesList = chatRepository.getAllMessages(userId1, userId2);
//        return writtenMessagesList.stream().map(ChatEntity::getDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<UserDto> getCountUnreadMessages(Long userId) {
//        List<UserInterface> countUnreadMesForUsers = chatRepository.getCountUnreadMessages(userId);
//
//        return countUnreadMesForUsers.stream().map(userInterface -> {
//            UserDto userDto = new UserDto();
//            BeanUtils.copyProperties(userInterface, userDto);
//            userDto.setEmailOrPhone(userInterface.getEmail_or_phone());
//            userDto.setCountUnreadMessages(userInterface.getCount_unread());
//            return userDto;
//        }).collect(Collectors.toList());
//    }
//
//    @Override
//    public ChatDto getUnreadMessage(Long userId1, Long userId2) {
//        Optional<ChatEntity> messageOpt = chatRepository.getUnreadMessage(userId1, userId2);
//        if(messageOpt.isPresent()) {
//            messageOpt.get().setRead(true);
//            chatRepository.save(messageOpt.get());
//            return messageOpt.get().getDto();
//        }
//        return null;
//    }
}
