package uz.tsx.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.bot.entity.UserBotEntity;

import java.util.Optional;


@Repository
public interface UserBotRepository extends JpaRepository<UserBotEntity,Long> {

    @Query(value = "SELECT tsxu.* FROM tsx_user_bot tsxu WHERE tsxu.chat_id = :chatId AND tsxu.status <> 'DELETED'", nativeQuery = true)
    Optional<UserBotEntity> getUserByChatId(@Param("chatId") String chatId);

    @Transactional
    @Modifying
    @Query(value = "WITH updated_users AS (\n" +
            "    SELECT tsxu.id\n" +
            "    FROM tsx_user tsxu\n" +
            "             INNER JOIN tsx_user_bot bot ON bot.user_entity_id = tsxu.id\n" +
            "    WHERE bot.chat_id = :chatId AND bot.status<>'DELETED'\n" +
            ")\n" +
            "UPDATE tsx_user\n" +
            "SET password = :passwordUser\n" +
            "WHERE id IN (SELECT id FROM updated_users) AND status<>'DELETED'", nativeQuery = true)
    void userAddPassword(@Param("chatId") String chatId,@Param("passwordUser") String passwordUser);

    @Query(value = "SELECT state FROM tsx_user_bot WHERE chat_id = :chatId AND status <> 'DELETED'", nativeQuery = true)
    String getUserState(@Param("chatId") String chatId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tsx_user_bot SET state = :state WHERE chat_id = :chatId AND status <> 'DELETED'", nativeQuery = true)
    void setUserState(@Param("chatId") String chatId, @Param("state") String state);

    // shaxsiy method test uchun
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tsx_user_bot WHERE chat_id = :chatId AND status <> 'DELETED'", nativeQuery = true)
    void deleteUserByChatId(@Param("chatId") String chatId);

    @Query(value = "SELECT language FROM tsx_user_bot WHERE chat_id = :chatId AND status <> 'DELETED'", nativeQuery = true)
    String getUserLang(@Param("chatId") String chatId);
}
