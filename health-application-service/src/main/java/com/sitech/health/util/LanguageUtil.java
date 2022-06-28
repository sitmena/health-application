package com.sitech.health.util;

import com.sitech.health.commons.ServiceConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class LanguageUtil {

    public String getRequestedLanguage(HttpServletRequest httpServletRequest) {
        String contentLang = httpServletRequest.getHeader(HttpHeaders.CONTENT_LANGUAGE);
        return StringUtils.isEmpty(contentLang) ?  ServiceConstants.ENGLISH_LANGUAGE : contentLang.equalsIgnoreCase(ServiceConstants.ARABIC_LANGUAGE) ? ServiceConstants.ARABIC_LANGUAGE : ServiceConstants.ENGLISH_LANGUAGE;
    }
}
