package com.sitech.health.controller;

import com.sitech.dbs.health_service.api.service.v2.SubscriptionApi;
import com.sitech.dbs.health_service.api.service.v2.model.Subscription;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.service.SubscriptionService;
import com.sitech.health.service.secuirty.UserContextService;
import com.sitech.health.util.LanguageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public class SubscriptionController implements SubscriptionApi {

    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private LanguageUtil languageUtil;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private UserContextService userContextService;

    @Override
    public ResponseEntity<Subscription> subscribe(Subscription subscription) {
        UserContextDto userContextLite = userContextService.getUserContextLite();
        return subscriptionService.subscribe(userContextLite , languageUtil.getRequestedLanguage(httpServletRequest) ,subscription);
    }
}
