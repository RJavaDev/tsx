package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tsx.entity.announcement.AnnouncementEntity;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long> {
    List<AnnouncementEntity> findAllBy();

}
