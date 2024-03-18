package uz.tsx.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.interfaces.AnnouncementInterface;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long> {
    List<AnnouncementEntity> findAllBy();

    @Query(value = "from AnnouncementEntity ae where ae.status <> 'DELETED'")
    Page<AnnouncementEntity> findPage(Pageable pageable);

    @Query(value = "select a.id, a.created_date as createdDate, a.title, ac.longitude, ac.latitude, ac.phone, ac.gmail, ac.address, \n" +
                   "       ap.price, ap.is_free as isFree, ap.is_deal as isDeal, ap.is_exchange as isExchange, c.id as currencyId, c.code as currencyCode\n" +
                   "from tsx_announcement a\n" +
                   "left join tsx_announcement_price ap on a.price_tag_id = ap.id\n" +
                   "left join tsx_announcement_contact ac on a.contact_info_id = ac.id\n" +
                   "left join tsx_currency c on ap.currency_id = c.id\n" +
                   "where a.status <> 'DELETED'",
            countQuery = "select count(*) from tsx_announcement where status <> 'DELETED'",
            nativeQuery = true)
    Page<AnnouncementInterface> findPageInterface(Pageable pageable);

    @Query(value = "select at.origin_name\n" +
                   "from announcement_attach aa \n" +
                   "left join tsx_attach at on at.id = aa.attach_id\n" +
                   "where aa.announcement_id = :announce_id", nativeQuery = true)
    List<String> getAttachImages(@Param("announce_id") Long announceId);
}
