package uz.tsx.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.tsx.entity.CategoryEntity;
import uz.tsx.entity.LikeEntity;
import uz.tsx.entity.UserEntity;
import uz.tsx.entity.announcement.AnnouncementEntity;

import java.util.List;
import java.util.Optional;
@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    @Transactional
    @Query(value = "SELECT l.announcement_id FROM tsx_like l WHERE l.created_by=:userId AND l.status<>'DELETED'",nativeQuery = true)
    List<Long> getMyLike(@Param("userId")  Long userId);


    @Transactional
    @Modifying
    @Query(value = "SELECT l.created_by FROM tsx_like l\n" +
            "                     INNER JOIN tsx_user u ON l.created_by=u.id\n" +
            "                     INNER JOIN tsx_announcement a ON l.announcement_id = a.id\n" +
            "WHERE a.created_by=:userId AND l.announcement_id=:announcementId\n" +
            "  AND l.status<>'DELETED'",nativeQuery = true)
    List<Long> myGetUsers(@Param("announcementId") Long announcementId, @Param("userId") Long userId);


   @Transactional
   @Modifying
    @Query(value ="UPDATE tsx_like SET like_status=0,status ='DELETED',updated_date=now() WHERE created_by=:userId\n" +
            "AND announcement_id =:announcementId AND status <> 'DELETED'",nativeQuery = true)
    void disLike(@Param("announcementId") Long announcementId, @Param("userId") Long userId);

    @Transactional
    @Query(value ="SELECT CASE WHEN status = 'DELETED' THEN 1 ELSE 0 END FROM tsx_like WHERE created_by=:userId AND announcement_id =:announcementId AND status <> 'DELETED' ",nativeQuery = true)
    Integer satatus(@Param("announcementId") Long announcementId, @Param("userId") Long userId);
}

