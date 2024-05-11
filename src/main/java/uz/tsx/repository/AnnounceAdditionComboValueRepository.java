package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.tsx.entity.announcement.additionInfo.AdditionComboValueEntity;

import java.util.List;
import java.util.Optional;

public interface AnnounceAdditionComboValueRepository extends JpaRepository<AdditionComboValueEntity, Long> {

    @Query(value = "SELECT tsxav.* FROM tsx_add_value tsxav WHERE tsxav.id = :comboId AND tsxav.status <> 'DELETED'", nativeQuery = true)
    Optional<AdditionComboValueEntity> getAdditionComboValueById(@Param("comboId")Long id);

    @Query(value = "SELECT tsxav.* FROM tsx_add_value tsxav WHERE tsxav.status <> 'DELETED'", nativeQuery = true)
    List<AdditionComboValueEntity> getAllAdditionComboValue();

    @Query(value = "SELECT COUNT(tsxav.id) > 0 FROM tsx_add_value tsxav WHERE tsxav.id = :comboId AND tsxav.status <> 'DELETED'", nativeQuery = true)
    boolean existsAdditionComboValue(@Param("comboId") Long id);

    @Modifying
    @Query(value = "UPDATE tsx_add_value SET status = 'DELETED' WHERE id = :comboId", nativeQuery = true)
    void delete(@Param("comboId")Long id);

    @Query(value = "SELECT tsxav.* FROM tsx_add_value tsxav WHERE tsxav.group_id = :comboGroupId  AND tsxav.status <> 'DELETED'", nativeQuery = true)
    List<AdditionComboValueEntity> getComboValueByGroupId(@Param("comboGroupId") Long id);

    @Modifying
    @Query(value = "SELECT ta.* FROM tsx_add_value ta\n" +
            "   LEFT JOIN tsx_add_group tg ON ta.group_id= tg.id\n" +
            "WHERE ta.group_id=:groupId AND ta.status<>'DELETED'", nativeQuery = true)
    List<AdditionComboValueEntity> additionComboValueListByGroupId(@Param("groupId")Long groupId);
}
