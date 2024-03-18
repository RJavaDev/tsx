package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.tsx.entity.announcement.AnnouncementContactEntity;

import java.util.Optional;

public interface AnnouncementContactRepository extends JpaRepository<AnnouncementContactEntity, Long> {

    @Query(value = "from AnnouncementContactEntity ac where ac.status <> 'DELETED' and ac.id = ?1")
    Optional<AnnouncementContactEntity> findBy(Long id);

}
