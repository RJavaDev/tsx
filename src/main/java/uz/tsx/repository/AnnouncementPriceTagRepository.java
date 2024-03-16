package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tsx.entity.announcement.AnnouncementPriceEntity;

public interface AnnouncementPriceTagRepository extends JpaRepository<AnnouncementPriceEntity, Long> {
}
