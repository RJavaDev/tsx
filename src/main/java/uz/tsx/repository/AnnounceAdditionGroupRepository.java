package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.tsx.entity.announcement.additionInfo.AdditionComboValueEntity;
import uz.tsx.entity.announcement.additionInfo.AdditionGroupEntity;

import java.util.List;

public interface AnnounceAdditionGroupRepository extends JpaRepository<AdditionGroupEntity, Long> {
    @Query(value = "from AdditionComboValueEntity ac where ac.groupId = :group_id")
    List<AdditionComboValueEntity> getAdditionGroupComboValues(@Param("group_id") Long groupId);

    @Query(value = "select * from tsx_add_group where status <> 'DELETED' and id in :group_ids", nativeQuery = true)
    List<AdditionGroupEntity> getAdditionGroupEntitiesByIds(@Param("group_ids") List<Long> groupIds);
}
