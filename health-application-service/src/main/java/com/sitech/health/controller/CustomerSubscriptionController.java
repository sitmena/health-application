package com.sitech.health.controller;

import com.sitech.dbs.health_service.api.service.v2.CustomerSubscriptionApi;
import com.sitech.dbs.health_service.api.service.v2.model.Subscription;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.SubscriptionEntity;
import com.sitech.health.mapper.CustomerSubscriptionMapper;
import com.sitech.health.service.CustomerSubscriptionService;
import com.sitech.health.service.secuirty.UserContextService;
import com.sitech.health.util.LanguageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class CustomerSubscriptionController implements CustomerSubscriptionApi {

    @Autowired
    private CustomerSubscriptionService customerSubscriptionService;
    @Autowired
    private LanguageUtil languageUtil;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private UserContextService userContextService;

    @Override
    public ResponseEntity<Subscription> customerSubscribe(Boolean activateDevice, Subscription subscription) {
        log.info("Start Calling Subscription [ {} ]", subscription.toString());
        boolean activateDeviceVal = Boolean.valueOf(httpServletRequest.getHeader("activateDevice"));
        UserContextDto userContextLite = userContextService.getUserContextLite();
        SubscriptionEntity subscriptionEntity = customerSubscriptionService.subscribeCustomer(userContextLite, languageUtil.getRequestedLanguage(httpServletRequest), subscription, activateDeviceVal);
        return ResponseEntity.status(HttpStatus.OK).body(CustomerSubscriptionMapper.INSTANCE.entityToDto(subscriptionEntity));
    }
}
