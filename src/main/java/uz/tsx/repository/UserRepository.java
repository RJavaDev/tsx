package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.tsx.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "SELECT tsxu.* FROM tsx_user tsxu WHERE tsxu.username = :username AND tsxu.status <> 'DELETED'", nativeQuery = true)
    Optional<UserEntity> getByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM tsx_user du WHERE du.username = :username OR du.phone_number = :phoneNumber", nativeQuery = true)
    List<UserEntity> findByUsernameOriginalDB(@Param("username") String username, @Param("phoneNumber") String phoneNumber);


    @Modifying
    @Query(value = "UPDATE tsx_user SET status = 'DELETED' WHERE id = :userId AND status <> 'DELETED'", nativeQuery = true)
    void userDelete(@Param("userId") Integer userId);



}
