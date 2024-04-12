package uz.tsx.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.interfaces.AnnouncementInterface;

import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long> {
    List<AnnouncementEntity> findAllBy();

    @Query(value = "from AnnouncementEntity ae where ae.status <> 'DELETED'")
    Page<AnnouncementEntity> findPage(Pageable pageable);

    @Query(value = "SELECT a.id, a.created_date AS createdDate, a.title, ac.longitude, ac.latitude, ac.phone, ac.gmail, ac.address, \n" +
                   "       ap.price, ap.is_free AS isFree, ap.is_deal AS isDeal, ap.is_exchange AS isExchange, c.id AS currencyId, c.code AS currencyCode\n" +
                   "    FROM tsx_announcement a\n" +
                   "    LEFT JOIN tsx_announcement_price ap ON a.price_tag_id = ap.id\n" +
                   "    LEFT JOIN tsx_announcement_contact ac ON a.contact_info_id = ac.id\n" +
                   "    LEFT JOIN tsx_currency c ON ap.currency_id = c.id\n" +
                   "    WHERE a.status <> 'DELETED' ORDER BY a.id DESC",
            countQuery = "SELECT count(*) FROM tsx_announcement WHERE status <> 'DELETED'",
            nativeQuery = true)
    Page<AnnouncementInterface> findPageInterface(Pageable pageable);

    @Query(value = "select at.origin_name\n" +
                   "from announcement_attach aa \n" +
                   "left join tsx_attach at on at.id = aa.attach_id\n" +
                   "where aa.announcement_id = :announce_id", nativeQuery = true)
    List<String> getAttachImages(@Param("announce_id") Long announceId);

    @Query(value = "SELECT tsxa.* FROM tsx_announcement tsxa WHERE tsxa.id = :announceId AND tsxa.status <> 'DELETE'", nativeQuery = true)
    Optional<AnnouncementEntity> getAnnouncementById(@Param("announceId") Long announceId);

    @Query(value = "SELECT tsxa.* FROM tsx_announcement tsxa " +
            "WHERE tsxa.status <> 'DELETE' " +
            "ORDER BY tsxa.id DESC",
            nativeQuery = true)
    Page<AnnouncementEntity> getAnnouncementPage(Pageable pageable);
}
