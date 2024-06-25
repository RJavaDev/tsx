package uz.tsx.service;

import uz.tsx.entity.CurrencyEntity;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    List<CurrencyEntity> getAllCurrencies();

    Optional<CurrencyEntity> getCurrencyByCode(String code);
}
