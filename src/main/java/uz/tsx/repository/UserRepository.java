package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.tsx.entity.UserEntity;
import uz.tsx.exception.interfaces.UserInterface;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT tsxu.* FROM tsx_user tsxu WHERE tsxu.email_or_phone = :username AND tsxu.status <> 'DELETED'", nativeQuery = true)
    Optional<UserEntity> getByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM tsx_user du WHERE du.email_or_phone = :username", nativeQuery = true)
    List<UserEntity> findByUsernameOriginalDB(@Param("username") String username);

    @Query(value = "SELECT tsxu.*, get_region_address(tsxu.region_id) AS address,tsxa.path, tsxa.type\n" +
            "            FROM tsx_user tsxu\n" +
            "            LEFT JOIN tsx_attach tsxa ON tsxu.attach_id = tsxa.id\n" +
            "            WHERE tsxu.id = :userId AND tsxu.status <> 'DELETED'\n" +
            "            AND NOT 'ADMIN' = ANY(role_enum_list)", nativeQuery = true)
    Optional<UserInterface> getUserInformation(@Param("userId") Long userId);

    @Query(value = "SELECT tsxu.*, get_region_address(tsxu.region_id) AS address,tsxa.path, tsxa.type\n" +
            "            FROM tsx_user tsxu\n" +
            "            LEFT JOIN tsx_attach tsxa ON tsxu.attach_id = tsxa.id\n" +
            "            WHERE tsxu.email_or_phone = :username AND tsxu.status <> 'DELETED'\n", nativeQuery = true)
    Optional<UserInterface> getUserByUsername(@Param("username") String username);

    @Query(value = "SELECT tsxu.*, get_region_address(tsxu.region_id) AS address,tsxa.path, tsxa.type " +
            "FROM tsx_user tsxu " +
            "LEFT JOIN tsx_attach tsxa ON tsxu.attach_id = tsxa.id " +
            "WHERE tsxu.status <> 'DELETED' " +
            "AND 'USER' = ANY(tsxu.role_enum_list)", nativeQuery = true)
    List<UserInterface> getAllUser();

    @Modifying
    @Query(value = "UPDATE tsx_user SET status = 'DELETED' WHERE id = :userId AND status <> 'DELETED'", nativeQuery = true)
    void userDelete(@Param("userId") Long userId);

}
