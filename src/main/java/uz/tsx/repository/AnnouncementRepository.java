package uz.tsx.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Query(value = "SELECT a.id, a.created_date AS createdDate,aa.attach_id AS attachId, a.title, ac.longitude, ac.latitude, ac.phone, ac.gmail, ac.address,\n" +
            "                     ap.price, ap.is_free AS isFree, ap.is_deal AS isDeal, ap.is_exchange AS isExchange, c.id AS currencyId, c.code AS currencyCode\n" +
            "                   FROM tsx_announcement a\n" +
            "                   LEFT JOIN tsx_announcement_price ap ON a.price_tag_id = ap.id\n" +
            "                   LEFT JOIN tsx_announcement_contact ac ON a.contact_info_id = ac.id\n" +
            "                   LEFT JOIN tsx_currency c ON ap.currency_id = c.id\n" +
            "                   LEFT JOIN announcement_attach aa ON a.id = aa.announcement_id\n" +
            "                   WHERE a.status <> 'DELETED' ORDER BY a.id DESC",
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
            "WHERE tsxa.status <> 'DE LETE' " +
            "ORDER BY tsxa.id DESC",
            nativeQuery = true)
    Page<AnnouncementEntity> getAnnouncementPage(Pageable pageable);

    @Query(value = "SELECT DISTINCT\n" +
            "    a.id,\n" +
            "    a.created_date AS createdDate,\n" +
            "    MAX(tsxa.id) AS attachId,  \n" +
            "    MAX(tsxa.path) AS attachPath,\n" +
            "    MAX(tsxa.type) AS attachType,\n" +
            "    a.i_saw AS ISaw,\n" +
            "    a.title,\n" +
            "    ac.address,\n" +
            "    ap.price,\n" +
            "    c.id AS currencyId,\n" +
            "    c.code AS currencyCode\n" +
            "FROM\n" +
            "    tsx_announcement a\n" +
            "        LEFT JOIN tsx_announcement_price ap ON a.price_tag_id = ap.id\n" +
            "        LEFT JOIN tsx_announcement_contact ac ON a.contact_info_id = ac.id\n" +
            "        LEFT JOIN announcement_attach aa ON a.id = aa.announcement_id\n" +
            "        LEFT JOIN tsx_attach tsxa ON tsxa.id = aa.attach_id\n" +
            "        LEFT JOIN tsx_currency c ON ap.currency_id = c.id\n" +
            "        INNER JOIN (\n" +
            "        SELECT *\n" +
            "        FROM (\n" +
            "                 WITH RECURSIVE category_parentId AS (\n" +
            "                     SELECT\n" +
            "                         tsxr_parent.id,\n" +
            "                         tsxr_parent.parent_id,\n" +
            "                         0 AS level\n" +
            "                     FROM\n" +
            "                         tsx_category tsxr_parent\n" +
            "                     WHERE\n" +
            "                         tsxr_parent.id =:categoryId \n" +
            "                     UNION ALL\n" +
            "                     SELECT\n" +
            "                         tsx_child.id,\n" +
            "                         tsx_child.parent_id,\n" +
            "                         level + 1\n" +
            "                     FROM\n" +
            "                         tsx_category tsx_child\n" +
            "                             INNER JOIN category_parentId rn ON rn.id = tsx_child.parent_id\n" +
            "                 )\n" +
            "                 SELECT\n" +
            "                     category_parentId.id\n" +
            "                 FROM\n" +
            "                     category_parentId\n" +
            "                 ORDER BY\n" +
            "                     category_parentId.level ASC\n" +
            "             ) AS user_address\n" +
            "    ) AS f ON f.id = a.category_id\n" +
            "WHERE\n" +
            "    a.status <> 'DELETED'\n" +
            "GROUP BY\n" +
            "    a.id, \n" +
            "    createdDate,\n" +
            "    ISaw,\n" +
            "    address,\n" +
            "    price,\n" +
            "    currencyId,\n" +
            "    currencyCode\n" +
            "ORDER BY\n" +
            "    a.id DESC",  countQuery = "SELECT\n" +
            "    count(a.id)\n" +
            "\n" +
            "FROM\n" +
            "    tsx_announcement a\n" +
            "\n" +
            "        INNER JOIN (\n" +
            "        SELECT *\n" +
            "        FROM (\n" +
            "                 WITH RECURSIVE category_parentId AS (\n" +
            "                     SELECT\n" +
            "                         tsxr_parent.id,\n" +
            "                         tsxr_parent.parent_id,\n" +
            "                         0 AS level\n" +
            "                     FROM\n" +
            "                         tsx_category tsxr_parent\n" +
            "                     WHERE\n" +
            "                         tsxr_parent.id =:categoryId \n" +
            "                     UNION ALL\n" +
            "                     SELECT\n" +
            "                         tsx_child.id,\n" +
            "                         tsx_child.parent_id,\n" +
            "                         level + 1\n" +
            "                     FROM\n" +
            "                         tsx_category tsx_child\n" +
            "                             INNER JOIN   category_parentId rn ON rn.id = tsx_child.parent_id\n" +
            "                 )\n" +
            "                 SELECT\n" +
            "                     category_parentId.id\n" +
            "                 FROM\n" +
            "                     category_parentId\n" +
            "                 ORDER BY\n" +
            "                     category_parentId.level ASC\n" +
            "             ) AS user_address\n" +
            "    ) AS f ON f.id = a.category_id\n" +
            "WHERE\n" +
            "    a.status <> 'DELETED'",nativeQuery = true)
    Page<AnnouncementInterface> getAnnouncementByCategoryId(Long categoryId, PageRequest of);
}
