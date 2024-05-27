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

    @Query(value = "with recursive sub_category as (\n" +
                   "    select * from tsx_category where id = :id\n" +
                   "    union all\n" +
                   "    select parent.* from tsx_category parent\n" +
                   "    inner join sub_category child on child.parent_id = parent.id\n" +
                   "    )\n" +
                   "select * from sub_category order by id desc", nativeQuery = true)
    List<CategoryEntity> getCategoriesByChildToAncestors(@Param("id") Long childId);

    @Query(value = "SELECT COUNT(tsxc.id) > 0 FROM tsx_category tsxc WHERE tsxc.id = :categoryId AND tsxc.status <> 'DELETED'", nativeQuery = true)
    boolean doesCategoryExistById(@Param("categoryId") Long categoryId);

    @Query(value = "SELECT tsxc.* FROM tsx_category tsxc WHERE tsxc.parent_id=:categoryId AND tsxc.status<>'DELETED'", nativeQuery = true)
    List<CategoryEntity> getChilddListById(@Param("categoryId") Long categoryId);

    @Query(value = "SELECT btsc.* FROM tsx_category btsc WHERE btsc.parent_id IS NULL AND btsc.status <> 'DELETED'", nativeQuery = true)
    List<CategoryEntity> getAllParentIsNull();
}
