package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.tsx.entity.chat.ChatMessageEntity;
import uz.tsx.interfaces.UserInterface;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {

    @Query(value = "select *\n" +
            "from tsx_chat_message\n" +
            "where ((sender_id = :user_id1 and recipient_id = :user_id2) or (recipient_id = :user_id1 and sender_id = :user_id2))\n" +
            "and status <> 'DELETED'", nativeQuery = true)
    List<ChatMessageEntity> getAllMessages(@Param("user_id1") Long userId1, @Param("user_id2") Long userId2);

    @Query(value = "select *\n" +
                   "from tsx_chat_message\n" +
                   "where ((sender_id = :user_id1 and recipient_id = :user_id2) or (recipient_id = :user_id1 and sender_id = :user_id2))\n" +
                   "and status <> 'DELETED' and is_read = false limit 1", nativeQuery = true)
    Optional<ChatMessageEntity> getUnreadMessage(@Param("user_id1") Long userId1,
                                                 @Param("user_id2") Long userId2);

    @Query(value = "select tu.id, tu.firstname, tu.lastname, tu.password, tu.email_or_phone, tc.count_unread\n" +
                   "from tsx_user tu\n" +
                   "inner join (\n" +
                   "    select recipient_id, count(*) as count_unread\n" +
                   "    from tsx_chat_message\n" +
                   "    where sender_id = :user_id and is_read = false and status <> 'DELETED'\n" +
                   "    group by recipient_id) tc on tc.recipient_id = tu.id", nativeQuery = true)
    List<UserInterface> getCountUnreadMessages(@Param("user_id") Long fromUserId);

    @Query(value = "from ChatMessageEntity where sender.id = :sender_id and recipient.id = :recipient_id")
    List<ChatMessageEntity> getChatMessages(@Param("sender_id") Long senderId,
                                            @Param("recipient_id") Long recipientId);

}
