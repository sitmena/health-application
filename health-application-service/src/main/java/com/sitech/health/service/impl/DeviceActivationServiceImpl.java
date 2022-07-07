package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.DeviceActivation;
import com.sitech.dbs.health_service.api.service.v2.model.Subscription;
import com.sitech.health.commons.ServiceConstants;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.DeviceStatus;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.service.DeviceActivationService;
import com.sitech.health.util.Translator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DeviceActivationServiceImpl implements DeviceActivationService {

    @Autowired
    private SubscriptionServiceImpl subscriptionService;
    private String lang;
    @Autowired
    private Translator translator;

    @Override
    public ResponseEntity<DeviceActivation> activateDevice(UserContextDto userContextLite, String requestedLanguage, DeviceActivation deviceActivation) {

        ResponseEntity<List<Subscription>> subLst = subscriptionService.getCustomerSubscription(userContextLite, requestedLanguage);
        List<Subscription> lst = subLst.getBody();
        Predicate<Subscription> isDeviceIdValid = subscript -> deviceActivation.getDeviceId().equals(subscript.getDeviceId());
        Optional<Subscription> subscribed = lst.stream().filter(isDeviceIdValid).findAny();

        ResponseEntity<Subscription> result = null;

        if (!subscribed.isPresent()) {
            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.NOT_VALID_DEVICE_ERROR, "not.valid.device.error.title", "not.valid.device.error.message", this.lang));
        } else {
            Map<Boolean, List<Subscription>> activeDeactivateLst = lst.stream().collect(Collectors.partitioningBy(subObject -> subObject.getDeviceStatus().name().equals(DeviceStatus.ACTIVE.name())));
            List<Subscription> activeLst = activeDeactivateLst.get(true);

            if (activeLst.size() >= 1) {
                for (Subscription subscription : activeLst) {
                    if (subscription.getDeviceId().equals(deviceActivation.getDeviceId())) {
                        result = subscriptionService.activateDeactivateDevice(subscription, true);
                    } else {
                        result = subscriptionService.activateDeactivateDevice(subscription, false);
                    }
                }
            }
            DeviceActivation deviceActivation1 = new DeviceActivation();
            Subscription sub  = result.getBody();
            deviceActivation1.setDeviceId(sub.getDeviceId());
            deviceActivation1.setId(sub.getId());
            return ResponseEntity.status(HttpStatus.OK).body(deviceActivation1);
        }

    }

}
