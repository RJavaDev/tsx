package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.tsx.entity.UsersEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    @Query(value = "SELECT tsxu.* FROM tsx_user tsxu WHERE tsxu.gmail = :gmail AND tsxu.status <> 'DELETED'", nativeQuery = true)
    Optional<UsersEntity> findByGmail(@Param("gmail") String gmail);


    @Modifying
    @Query(value = "UPDATE tsx_user SET status = 'DELETED' WHERE id = :userId AND status <> 'DELETED' AND NOT 'SUPER_ADMIN' = ANY(role_enum_list)", nativeQuery = true)
    Integer userDelete(@Param("userId") Integer userId);

}
