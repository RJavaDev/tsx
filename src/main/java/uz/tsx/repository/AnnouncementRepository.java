package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tsx.entity.announcement.AnnouncementEntity;

import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long> {
    List<AnnouncementEntity> findAllBy();
    Optional<AnnouncementEntity> findById(Long id);

}
