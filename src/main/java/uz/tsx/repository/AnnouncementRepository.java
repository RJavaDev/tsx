package uz.tsx.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.interfaces.AnnouncementInterface;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long> {
    List<AnnouncementEntity> findAllBy();

    @Query(value = "from AnnouncementEntity ae where ae.status <> 'DELETED'")
    Page<AnnouncementEntity> findPage(Pageable pageable);

    @Query(value = "SELECT a.id, a.created_date AS createdDate,aa.attach_id AS attachId, a.title, ac.phone, ac.gmail, ac.address,\n" +
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

    @Query(value = "SELECT \n" +
            "    a.id,\n" +
            "    a.created_date AS createdDate,\n" +
            "    MIN(concat(tsxa.path, tsxa.id ||'.'|| tsxa.type)) AS attachPath,\n" +
            "    get_region_address(ac.region_id) as address,\n" +
            "    a.i_saw AS ISaw,\n" +
            "    a.title,\n" +
            "    ap.price,\n" +
            "    c.id AS currencyId,\n" +
            "    c.code AS currencyCode\n" +
            "FROM\n" +
            "    tsx_announcement a\n" +
            "        LEFT JOIN tsx_announcement_price ap ON a.price_tag_id = ap.id\n" +
            "        LEFT JOIN tsx_announcement_contact ac ON a.contact_info_id = ac.id\n" +
            "        LEFT JOIN announcement_attach aa ON a.id = aa.announcement_id \n" +
            "        LEFT JOIN tsx_attach tsxa ON tsxa.id = aa.attach_id\n" +
            "        LEFT JOIN tsx_currency c ON ap.currency_id = c.id\n" +
            "WHERE\n" +
            "    a.status <> 'DELETED' AND a.is_active <> false \n" +
            "GROUP BY\n" +
            "    a.id,\n" +
            "    c.id,\n" +
            "    ap.price,\n" +
            "    ac.id\n" +
            "ORDER BY a.id DESC",
            nativeQuery = true)
    Page<AnnouncementInterface> getAnnouncementPage(Pageable pageable);

    @Query(value = "SELECT \n" +
            "    a.id,\n" +
            "    a.created_date AS createdDate,\n" +
            "    MIN(concat(tsxa.path, tsxa.id ||'.'|| tsxa.type)) AS attachPath,\n" +
            "    a.i_saw AS iSaw,\n" +
            "    a.title,\n" +
            "    tc.router AS router,\n" +
            "    ap.price,\n" +
            "    c.id AS currencyId,\n" +
            "    c.code AS currencyCode,\n" +
            "    get_region_address(a.category_id) as address\n" +
            "FROM\n" +
            "    tsx_announcement a\n" +
            "        LEFT JOIN tsx_announcement_price ap ON a.price_tag_id = ap.id\n" +
            "        LEFT JOIN tsx_announcement_contact ac ON a.contact_info_id = ac.id\n" +
            "        LEFT JOIN announcement_attach aa ON a.id = aa.announcement_id\n" +
            "        LEFT JOIN tsx_attach tsxa ON tsxa.id = aa.attach_id\n" +
            "        LEFT JOIN tsx_currency c ON ap.currency_id = c.id\n" +
            "        LEFT JOIN tsx_category tc ON tc.id= :categoryId\n" +
            "        INNER JOIN (\n" +
            "        SELECT * FROM (\n" +
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
            "                 SELECT category_parentId.id FROM\n" +
            "                     category_parentId\n" +
            "                 ORDER BY\n" +
            "                     category_parentId.level\n" +
            "             ) AS user_address\n" +
            "    ) AS f ON f.id = a.category_id\n" +
            "WHERE a.status <> 'DELETED' AND a.is_active <> false \n" +
            "GROUP BY a.id, tc.id,ap.id,c.id\n" +
            "ORDER BY a.id DESC" ,nativeQuery = true)
    Page<AnnouncementInterface> getAnnouncementByCategoryId(Long categoryId, PageRequest of);

    @Query(value = "SELECT\n" +
            "    tsxa.id,\n" +
            "    tsxa.created_date AS createdDate,\n" +
            "    MIN(concat(tsxphoto.path, tsxphoto.id ||'.'|| tsxphoto.type)) AS attachPath,\n" +
            "    tsxa.i_saw AS iSaw,\n" +
            "    tsxa.title,\n" +
            "    ap.price,\n" +
            "    c.id AS currencyId,\n" +
            "    c.code AS currencyCode,\n" +
            "    get_region_address(tsxac.region_id) as address \n" +
            "FROM tsx_announcement tsxa\n" +
            "         LEFT JOIN (\n" +
            "    SELECT DISTINCT ON (aa.announcement_id) aa.announcement_id, tsxphoto.id, tsxphoto.path, tsxphoto.type\n" +
            "    FROM announcement_attach aa\n" +
            "             LEFT JOIN tsx_attach tsxphoto ON tsxphoto.id = aa.attach_id\n" +
            ") tsxphoto ON tsxa.id = tsxphoto.announcement_id\n" +
            "         LEFT JOIN tsx_announcement_price ap ON tsxa.price_tag_id = ap.id\n" +
            "         LEFT JOIN tsx_announcement_contact tsxac ON tsxa.contact_info_id = tsxac.id\n" +
            "         LEFT JOIN tsx_currency c ON ap.currency_id = c.id\n" +
            "WHERE tsxa.status <> 'DELETED' AND tsxa.is_active <> false AND \n" +
            "    (:categoryId IS NULL OR tsxa.category_id = (\n" +
            "        SELECT id\n" +
            "        FROM ( WITH RECURSIVE category_parentId AS (\n" +
            "            SELECT tsxr_parent.id,tsxr_parent.parent_id FROM tsx_category tsxr_parent\n" +
            "            WHERE tsxr_parent.id = :categoryId\n" +
            "            UNION ALL\n" +
            "            SELECT tsx_child.id,tsx_child.parent_id FROM\n" +
            "                tsx_category tsx_child\n" +
            "                    INNER JOIN category_parentId rn ON rn.id = tsx_child.parent_id\n" +
            "        )\n" +
            "               SELECT\n" +
            "                   category_parentId.id\n" +
            "               FROM\n" +
            "                   category_parentId\n" +
            "             ) as category where category.id = tsxa.category_id))\n" +
            "  AND (:regionId IS NULL OR tsxac.region_id = (\n" +
            "    SELECT id\n" +
            "    FROM ( WITH RECURSIVE region_parentId AS (\n" +
            "        SELECT tsxr_parent.id, tsxr_parent.parent_id FROM tsx_region tsxr_parent\n" +
            "        WHERE tsxr_parent.id = :regionId\n" +
            "        UNION ALL\n" +
            "        SELECT tsx_child.id, tsx_child.parent_id  FROM tsx_region tsx_child\n" +
            "           INNER JOIN region_parentId rn ON rn.id = tsx_child.parent_id\n" +
            "           )\n" +
            "           SELECT region_parentId.id FROM region_parentId\n" +
            "         ) as region WHERE region.id = tsxac.region_id)\n" +
            "      )\n" +
            "  AND tsxa.created_date BETWEEN\n" +
            "    COALESCE(:startDate, CAST('2024-05-04 13:44:38' AS TIMESTAMP WITHOUT TIME ZONE))\n" +
            "    AND COALESCE(:endDate, NOW())\n" +
            "    AND (:searchTitle IS NULL OR similarity(tsxa.title, :searchTitle) > 0.2)\n" +
            "GROUP BY\n" +
            "    tsxa.id,tsxac.id,tsxa.title, ap.id,c.id\n" +
            "ORDER BY similarity(tsxa.title, :searchTitle) DESC", nativeQuery = true)
    Page<AnnouncementInterface> searchAnnouncementAndFilter(@Param("searchTitle")String searchTitle, @Param("regionId")Long regionId, @Param("categoryId")Long categoryId, @Param("startDate")Date startDate, @Param("endDate")Date endDate, Pageable pageable);

    @Query(value = "SELECT * FROM tsx_announcement WHERE user_id = :userId and status <> 'DELETED' order by id desc", nativeQuery = true)
    List<AnnouncementEntity> getAnnouncementListByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tsx_announcement SET is_active = :isActive WHERE id = :announcementId", nativeQuery = true)
    void changeActiveStatus(@Param("announcementId") Long announcementId, @Param("isActive") Boolean isActive);


    @Modifying
    @Transactional
    @Query(value = "UPDATE tsx_announcement SET status = 'DELETED' WHERE id = :announcementId", nativeQuery = true)
    void delete(@Param("announcementId") Long announcementId);

}
