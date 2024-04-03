package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.tsx.entity.announcement.option.OptionEntity;

import java.util.List;

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
}
