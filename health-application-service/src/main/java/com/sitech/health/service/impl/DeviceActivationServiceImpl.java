package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.DeviceActivation;
import com.sitech.dbs.health_service.api.service.v2.model.Subscription;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.DeviceStatus;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.service.DeviceActivationService;
import com.sitech.health.util.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
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

        Predicate<Subscription> isDeviceActive = subscript -> subscript.getDeviceStatus().getValue() == DeviceStatus.ACTIVE.name();

        Subscription subscription = lst.stream()
                .filter(isDeviceIdValid)
                .findAny()
                .orElseThrow(() -> new GenericErrorException(String.format("Device %s Not Valid.", deviceActivation.getDeviceId())));


        List<Subscription> deActivateLst = lst.stream()
                .filter(subscript -> subscript.getDeviceStatus().getValue() != DeviceStatus.ACTIVE.name())
                .collect(Collectors.toList());


        subscriptionService.activateDevice(subscription);
        return null;
    }


    //.collect(Collectors.toList());

//        lst.stream()
//                .forEach(i -> {
//                    if (i.getDeviceId().equals(fitnessData.getDeviceId()) && i.getIsDeviceActive().equals(true)) {
//                        return true;
//                    } else {
//                        throw new GenericErrorException("");
//                    }
//                });


//        activateDevice(lst,fitnessData);
//
//        Subscription matchingPerson = lst.stream()
//                .filter(
//                        subscript -> fitnessData.getDeviceId().equals(subscript.getDeviceId())
//                        &&
//                        subscript.getIsDeviceActive().booleanValue()
//                )
//                    .findAny().orElseThrow(() -> new GenericErrorException(String.format("Device %s Not Activated.", fitnessData.getDeviceId())));
////                .findAny().orElse(activateDevice(fitnessData));
//
//        // subscriptionService.activateDevice()

    //
//    private boolean activateDevice(List<Subscription> lst, FitnessData fitnessData) {
//        if(fitnessData.getAdditions().get("ACTIVATE_DEVICE").equals(Boolean.valueOf("true"))){
//
//subscriptionService.
//        }else{
//            throw new GenericErrorException(String.format("Device %s Not Activated.", fitnessData.getDeviceId()));
//        }
//        return false;

//    private Supplier<? extends Subscription> activateDevice() {
//    }
}
