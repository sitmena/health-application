package com.sitech.health.service;

import com.sitech.dbs.health_service.api.service.v2.model.DeviceInfo;
import com.sitech.health.domain.SubscriptionEntity;

import java.util.List;

public interface CustomerSubscriptionService {

    List<SubscriptionEntity> getCustomerSubscriptions();
    List<SubscriptionEntity> getCustomerSubscriptionsAndDeviceStatus();

    SubscriptionEntity addCustomerSubscription(DeviceInfo deviceInfo);

    boolean isCustomerSubscribed();

    boolean isDeviceActive(DeviceInfo deviceInfo);

    SubscriptionEntity activateDevice(DeviceInfo deviceInfo);

}
