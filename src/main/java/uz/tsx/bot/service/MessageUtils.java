package uz.tsx.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MessageUtils {

    private final MessageSource messageSource;

    public String getMessage(String key, String language){
        return getLocalizedMessage(key, language);
    }

    public String getLocalizedMessage(String key, String language) {
        if(Objects.isNull(language)) language = "uz";
        Locale locale = new Locale(language);
        LocaleContextHolder.setLocale(locale);  // Set the locale for the context
        return messageSource.getMessage(key, null, locale);
    }
}
