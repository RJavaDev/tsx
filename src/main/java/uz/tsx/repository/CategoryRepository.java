package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.tsx.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query(value = "SELECT btsc.* FROM tsx_category btsc WHERE btsc.id=:categoryId AND btsc.status <> 'DELETED'", nativeQuery = true)
    Optional<CategoryEntity> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query(value = "SELECT btsc.* FROM tsx_category btsc WHERE btsc.status <> 'DELETED'", nativeQuery = true)
    List<CategoryEntity> findAllCategory();


    @Modifying
    @Query(value = "WITH RECURSIVE sub_category AS (\n" +
            "        SELECT * FROM tsx_category WHERE id = :categoryId\n" +
            "        UNION ALL\n" +
            "        SELECT child.* FROM tsx_category child\n" +
            "        INNER JOIN\n" +
            "        sub_category parent ON parent.id=child.parent_id\n" +
            ")UPDATE tsx_category SET status = 'DELETED' WHERE id IN(SELECT id FROM sub_category)", nativeQuery = true)
    void categoryDelete(@Param("categoryId") Long categoryId);

    @Query(value = "SELECT btsc.* FROM tsx_category btsc WHERE btsc.name_en=:categoryName",nativeQuery = true)
    CategoryEntity findByCategoryName(@Param("categoryName")String categoryName);

    @Query(value = "SELECT btsc.* FROM tsx_category btsc WHERE (btsc.id=:parentId OR btsc.id=:childId) AND btsc.status <> 'DELETED'", nativeQuery = true)
    List<CategoryEntity> getCategoryIdParentAndChild(@Param("parentId") Long parentId, @Param("childId") Long childId);

    @Query(value = "SELECT btsc.* FROM tsx_category btsc WHERE btsc.parent_id IS NULL", nativeQuery = true)
    List<CategoryEntity> getCategoryTree();
}
