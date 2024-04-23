package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.tsx.entity.chat.ChatRoomEntity;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {

    @Query(value = "from ChatRoomEntity cr where cr.announcement.id = :announce_id")
    Optional<ChatRoomEntity> getChatRoomByAnnounceId(@Param("announce_id") Long announceId);
}
