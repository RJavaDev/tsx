package uz.tsx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.tsx.entity.CurrencyEntity;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {

    @Query(value = "SELECT COUNT(tsxc.id) > 0 FROM tsx_currency tsxc WHERE tsxc.id = :currencyId AND tsxc.status <> 'DELETED'", nativeQuery = true)
    boolean existsCurrencyId(@Param("currencyId") Long id);

    @Query(value = "SELECT * FROM tsx_currency tsxc WHERE tsxc.code = :code AND tsxc.status <> 'DELETED'", nativeQuery = true)
    Optional<CurrencyEntity> getCurrencyEntityByCode(@Param("code") String code);
}
