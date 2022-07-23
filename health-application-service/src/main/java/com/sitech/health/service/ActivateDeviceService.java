package com.sitech.health.service;

import com.sitech.dbs.health_service.api.service.v2.model.DeviceInfo;
import com.sitech.health.domain.SubscriptionEntity;

public interface ActivateDeviceService {

    boolean isDeviceActive(DeviceInfo deviceInfo);

    SubscriptionEntity activateDevice(DeviceInfo deviceInfo);

    //
//    SubscriptionEntity subscribeCustomer(UserContextDto userContextLite, String language, DeviceActivation deviceActivation);
//
//    List<SubscriptionEntity> getCustomerSubscriptions(UserContextDto userContextLite, String language);
//
//    boolean isCustomerSubscribed(UserContextDto userContextLite, String language );
//
//    boolean isDeviceActive(UserContextDto userContextLite, String language ,  DeviceActivation deviceActivation);
//
//    SubscriptionEntity activateDevice(UserContextDto userContextLite, String language , DeviceActivation deviceActivation);
//
//    SubscriptionEntity addCustomerSubscription(UserContextDto userContextLite, String language , DeviceActivation deviceActivation);
}
