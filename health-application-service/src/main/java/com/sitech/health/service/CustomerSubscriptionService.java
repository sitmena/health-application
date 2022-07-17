package com.sitech.health.service;

import com.sitech.dbs.health_service.api.service.v2.model.Subscription;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.SubscriptionEntity;

import java.util.List;

public interface CustomerSubscriptionService {

    SubscriptionEntity subscribeCustomer(UserContextDto userContextLite, String requestedLanguage, Subscription subscription, boolean activateDevice);

    List<SubscriptionEntity> getCustomerSubscriptions(UserContextDto userContextLite, String requestedLanguage);

    SubscriptionEntity addCustomerSubscription(UserContextDto userContextLite, Subscription subscription, String requestedLanguage);

    SubscriptionEntity updateCustomerSubscription(UserContextDto userContextLite, SubscriptionEntity subscriptionEntity);

    boolean isDeviceSubscribed(UserContextDto userContextLite, Subscription subscription, String requestedLanguage);

    boolean isDeviceActive(UserContextDto userContextLite, Subscription subscription, String requestedLanguage);

    SubscriptionEntity activateDevice(UserContextDto userContextLite, Subscription subscription, String requestedLanguage);
}
