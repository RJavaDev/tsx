package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.tsx.entity.chat.ChatStatus;
import uz.tsx.entity.chat.ChatUserEntity;

import java.util.List;
import java.util.Optional;

public interface ChatUserRepository extends JpaRepository<ChatUserEntity, Long> {
    @Query(value = "from ChatUserEntity cu where cu.user.id = :user_id and cu.chatRoom.id = :room_id")
    Optional<ChatUserEntity> getChatUserByUserIdAndRoomId(@Param("user_id") Long userId,
                                                          @Param("room_id") Long roomId);


    @Query(value = "from ChatUserEntity cu where cu.chatRoom.id = :room_id and cu.chatStatus = :chat_status")
    List<ChatUserEntity> findAllByChatStatusAndRoomId(@Param("room_id") Long chatRoomId,
                                                      @Param("chat_status")ChatStatus chatStatus);
}
