package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.tsx.entity.announcement.option.OptionEntity;

import java.util.List;
import java.util.Map;

public interface OptionRepository extends JpaRepository<OptionEntity, Long> {

    @Query(value = "SELECT * FROM tsx_option WHERE id IN :optionId", nativeQuery = true)
    List<OptionEntity> findAllOptionByIds(@Param("optionId") List<Long> optionId);

   @Query(value = "select * from tsx_option where status <> 'DELETED' and (:group_id is null or group_id = :group_id)", nativeQuery = true)
   List<OptionEntity> findAllOptionsByGroupId(@Param("group_id") Long groupId);

    @Query(value = "SELECT tsxo.* FROM tsx_option tsxo WHERE tsxo.id = :optionId AND tsxo.status <> 'DELETED'", nativeQuery = true)
    OptionEntity getOptionById(@Param("optionId") Long optionId);

    @Query(value = "SELECT tsxo.* FROM tsx_option tsxo WHERE tsxo.status <> 'DELETED'", nativeQuery = true)
    List<OptionEntity> getAllOptions();

    @Modifying
    @Query(value = "UPDATE tsx_option SET status = 'DELETED' WHERE id = :optionId AND status <> 'DELETED'", nativeQuery = true)
    void delete(@Param("optionId") Long id);

    @Modifying
    @Query(value = "SELECT t1.* ,op.name_ru,op.name_eu,op.name_uz FROM tsx_option t1\n" +
            "   LEFT JOIN tsx_category tc ON t1.category_id = tc.id\n" +
            "   LEFT JOIN tsx_option_group op ON t1.group_id = op.id\n" +
            "WHERE t1.category_id=:categoryId AND t1.status<>'DELETED'", nativeQuery = true)
    List<Map<Object,Object>> optionListByCategoryId(@Param("categoryId") Long categoryId);
}
