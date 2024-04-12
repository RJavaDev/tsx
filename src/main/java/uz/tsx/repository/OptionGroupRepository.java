package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.tsx.entity.announcement.option.OptionGroupEntity;

import java.util.List;
import java.util.Optional;

public interface OptionGroupRepository extends JpaRepository<OptionGroupEntity, Long> {

    @Query(value = "SELECT tsxog.* FROM tsx_option_group tsxog where tsxog.id = :id AND tsxog.status <> 'DELETED'", nativeQuery = true)
    Optional<OptionGroupEntity> getOption(@Param("id") Long id);

    @Query(value = "SELECT tsxog.* FROM tsx_option_group tsxog where tsxog.status <> 'DELETED'", nativeQuery = true)
    List<OptionGroupEntity> getAllOptionGroup();


    @Modifying
    @Query(value = "UPDATE tsx_option_group SET status = 'DELETED' WHERE id = :optionGroupId AND status <> 'DELETED'", nativeQuery = true)
    void delete(@Param("optionGroupId")Long optionGroupId);

    @Query(value = "SELECT COUNT(tsxog.id) > 0 FROM tsx_option_group tsxog WHERE tsxog.id = :id AND tsxog.status <> 'DELETED'", nativeQuery = true)
    boolean existsByGroupId(@Param("id") Long optionGroupId);
}
