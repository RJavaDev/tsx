package uz.tsx.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.entity.AttachEntity;

import java.util.List;

public interface AttachRepository extends CrudRepository<AttachEntity, String>, PagingAndSortingRepository<AttachEntity, String> {

    @Query(value = "SELECT btsa.* FROM tsx_attach btsa WHERE btsa.id IN (:attachIdList)",nativeQuery = true)
    List<AttachEntity> getAttachListById(@Param("attachIdList") List<String> attachIdList);

    @Query(value = "SELECT btsa.id FROM tsx_attach btsa WHERE btsa.id IN (:attachIdList)",nativeQuery = true)
    List<String> getAttachListByIds(@Param("attachIdList") List<String> attachIdList);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM announcement_attach WHERE attach_id = :attachId", nativeQuery = true)
    void deleteAnnouncementAttachByAttachId(@Param("attachId") String attachId);
}
