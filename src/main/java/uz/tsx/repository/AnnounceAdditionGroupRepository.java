package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.tsx.entity.announcement.additionInfo.AdditionComboValueEntity;
import uz.tsx.entity.announcement.additionInfo.AdditionGroupEntity;

import java.util.List;
import java.util.Optional;

public interface AnnounceAdditionGroupRepository extends JpaRepository<AdditionGroupEntity, Long> {
    @Query(value = "from AdditionComboValueEntity ac where ac.groupId = :group_id")
    List<AdditionComboValueEntity> getAdditionGroupComboValues(@Param("group_id") Long groupId);

    @Query(value = "select * from tsx_add_group where status <> 'DELETED' and id in :group_ids", nativeQuery = true)
    List<AdditionGroupEntity> getAdditionGroupEntitiesByIds(@Param("group_ids") List<Long> groupIds);

    @Query(value = "SELECT tsxag.* FROM tsx_add_group tsxag WHERE tsxag.id = :additionGroupId AND tsxag.status <> 'DELETED'", nativeQuery = true)
    Optional<AdditionGroupEntity> getAdditionGroupById(Long id);

    @Query(value = "SELECT tsxag.* FROM tsx_add_group tsxag WHERE tsxag.status <> 'DELETED'", nativeQuery = true)
    List<AdditionGroupEntity> getAllAdditionGroup();

    @Query(value = "SELECT COUNT(tsxog.id) > 0 FROM tsx_add_group tsxog WHERE tsxog.id = :additionGroupId AND tsxog.status <> 'DELETED'", nativeQuery = true)
    boolean existsByAdditionGroupId(@Param("additionGroupId") Long id);

    @Modifying
    @Query(value = "UPDATE tsx_add_group SET status = 'DELETED' WHERE id = :additionGroupId AND status <> 'DELETED'", nativeQuery = true)
    void delete(@Param("additionGroupId") Long id);
}
