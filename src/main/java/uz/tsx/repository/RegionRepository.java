package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.tsx.entity.RegionEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity,Integer> {

    @Query(value = "SELECT tsxr.* FROM tsx_region tsxr WHERE tsxr.name=:regionName",nativeQuery = true)
    RegionEntity findByRegionName(@Param("regionName")String regionName);

    @Query(value = "SELECT tsxr.* FROM tsx_region tsxr WHERE tsxr.id=:regionId AND tsxr.status <> 'DELETED'", nativeQuery = true)
    Optional<RegionEntity> findByRegionId(@Param("regionId") Integer regionId);

    @Query(value = "SELECT tsxr.* FROM tsx_region tsxr WHERE tsxr.status <> 'DELETED'", nativeQuery = true)
    List<RegionEntity> findAllRegion();

    @Query(value = "SELECT tsxr.* FROM tsx_region tsxr WHERE (tsxr.id=:parentId OR tsxr.id=:childId) AND tsxr.status <> 'DELETED'", nativeQuery = true)
    List<RegionEntity> getRegionIdParentAndChild(@Param("parentId") Integer parentId, @Param("childId") Integer childId);

    @Query(value = "SELECT tsxr.* FROM tsx_region tsxr WHERE tsxr.parent_id IS NULL AND tsxr.status <> 'DELETED'", nativeQuery = true)
    List<RegionEntity> getRegionAllTree();

    @Modifying
    @Query(value = "WITH RECURSIVE sub_region AS (\n" +
            "        SELECT * FROM tsx_region WHERE id = :regionId\n" +
            "        UNION ALL\n" +
            "        SELECT child.* FROM tsx_region child\n" +
            "        INNER JOIN\n" +
            "        sub_region parent ON parent.id=child.parent_id\n" +
            ")UPDATE tsx_region SET status = 'DELETED' WHERE id IN(SELECT id FROM sub_region)", nativeQuery = true)
    void regionDelete(@Param("regionId") Integer regionId);


    @Query(value = "SELECT tsxr.* FROM tsx_region tsxr WHERE tsxr.id = :regionId AND tsxr.status <> 'DELETED'", nativeQuery = true)
    Optional<RegionEntity> getRegionId(@Param("regionId") Integer regionId);
}