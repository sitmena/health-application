package com.sitech.health.util;

import com.sitech.health.commons.ServiceConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;

@Component
public class Translator {

    private final ResourceBundleMessageSource messageSource;

    List<Locale> acceptedLanguages = Arrays.asList(new Locale(ServiceConstants.ARABIC_LANGUAGE), new Locale(ServiceConstants.ENGLISH_LANGUAGE));

    @Autowired
    Translator(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String toLocale(String message, String requestedLang) {
        Locale locale;
        if (StringUtils.isNotEmpty(requestedLang) && requestedLang.length() == 2) {
            locale = acceptedLanguages
                    .stream()
                    .filter(l -> l.getLanguage().equalsIgnoreCase(requestedLang))
                    .findFirst()
                    .orElse(acceptedLanguages.get(0));
        } else {
            locale = acceptedLanguages.get(0);
        }
        return messageSource.getMessage(message, null, locale);
    }

    public String getTranslatedKey(String code, String title, String message, String lang) {
        StringJoiner joiner = new StringJoiner("~");
        joiner.add(code);
        joiner.add(toLocale(title, lang));
        joiner.add(toLocale(message, lang));
        return joiner.toString();

    }

    public String toArLocale(String msgCode) {
        return messageSource.getMessage(msgCode, null, new Locale(ServiceConstants.ARABIC_LANGUAGE));
    }

    public String toEnLocale(String msgCode) {
        return messageSource.getMessage(msgCode, null, new Locale(ServiceConstants.ENGLISH_LANGUAGE));
    }

}