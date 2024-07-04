package uz.tsx.bot.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Objects;

@Component
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
