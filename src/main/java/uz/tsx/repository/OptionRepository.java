package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.tsx.entity.announcement.option.OptionEntity;

import java.util.List;

public interface OptionRepository extends JpaRepository<OptionEntity, Long> {

    @Query(value = "select * from tsx_option where id in :option_ids", nativeQuery = true)
    List<OptionEntity> findAllOptionByIds(@Param("option_ids") List<Long> optionIds);

}
