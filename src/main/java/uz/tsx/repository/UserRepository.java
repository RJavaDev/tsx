package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.entity.UserEntity;
import uz.tsx.interfaces.UserInterface;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "SELECT tsxu.* FROM tsx_user tsxu WHERE tsxu.username = :username AND tsxu.status <> 'DELETED'", nativeQuery = true)
    Optional<UserEntity> getByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM tsx_user du WHERE du.username = :username OR du.phone_number = :phoneNumber", nativeQuery = true)
    List<UserEntity> findByUsernameOriginalDB(@Param("username") String username, @Param("phoneNumber") String phoneNumber);
    @Modifying
    @Transactional
    @Query(value = "SELECT tsxu.*, get_region_address(tsxu.region_id) AS address_en,get_region_address_ru(tsxu.region_id) AS address_ru,get_region_address_uz(tsxu.region_id) AS address_uz,tsxa.path, tsxa.type " +
            "FROM tsx_user tsxu " +
            "LEFT JOIN tsx_attach tsxa ON tsxu.attach_id = tsxa.id " +
            "WHERE tsxu.id = :userId AND status <> 'DELETED' " +
            "AND NOT 'ADMIN' = ANY(role_enum_list)", nativeQuery = true)
    Optional<UserInterface> getUserInformation(@Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query(value = "SELECT tsxu.*, get_region_address(tsxu.region_id) AS address_en,get_region_address_ru(tsxu.region_id) AS address_ru,get_region_address_uz(tsxu.region_id) AS address_uz,tsxa.path, tsxa.type " +
            "FROM tsx_user tsxu " +
            "LEFT JOIN tsx_attach tsxa ON tsxu.attach_id = tsxa.id " +
            "WHERE tsxu.status <> 'DELETED' " +
            "AND 'USER' = ANY(tsxu.role_enum_list)", nativeQuery = true)
    List<UserInterface> getAllUser();

    @Modifying
    @Query(value = "UPDATE tsx_user SET status = 'DELETED' WHERE id = :userId AND status <> 'DELETED'", nativeQuery = true)
    void userDelete(@Param("userId") Integer userId);
}
