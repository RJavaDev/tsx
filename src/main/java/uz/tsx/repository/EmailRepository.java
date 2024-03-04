package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.tsx.entity.EmailEntity;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<EmailEntity, Integer> {

    @Query(value = "SELECT em.* FROM tsx_email em WHERE em.email=:email", nativeQuery = true)
    Optional<EmailEntity>getEmail(@Param("email")String email);
//    Optional<EmailEntity> findByEmail(String email);
}
