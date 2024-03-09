package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.entity.EmailEntity;

import java.util.List;
import java.util.Optional;

public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
    @Transactional
    @Query(value = "SELECT * FROM tsx_email  ",nativeQuery = true)
    List<EmailEntity>getAll();

    @Query(value = "SELECT em.* FROM tsx_email em WHERE em.email=:email", nativeQuery = true)
    Optional<EmailEntity>getEmail(@Param("email")String email);

}
