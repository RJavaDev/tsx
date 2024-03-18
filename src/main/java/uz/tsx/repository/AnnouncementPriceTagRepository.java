package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.tsx.entity.announcement.AnnouncementPriceEntity;

import java.util.Optional;

public interface AnnouncementPriceTagRepository extends JpaRepository<AnnouncementPriceEntity, Long> {

    @Query(value = "from AnnouncementPriceEntity ap where ap.status <> 'DELETED' and ap.id = ?1")
    Optional<AnnouncementPriceEntity> findBy(Long id);
}
