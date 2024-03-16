package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tsx.entity.announcement.AnnouncementContactEntity;

public interface AnnouncementContactRepository extends JpaRepository<AnnouncementContactEntity, Long> {
}
