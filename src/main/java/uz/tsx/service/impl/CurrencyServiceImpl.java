package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsx.entity.CurrencyEntity;
import uz.tsx.repository.CurrencyRepository;
import uz.tsx.service.CurrencyService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    @Override
    public List<CurrencyEntity> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Optional<CurrencyEntity> getCurrencyByCode(String code) {
        return currencyRepository.getCurrencyEntityByCode(code);
    }
}
