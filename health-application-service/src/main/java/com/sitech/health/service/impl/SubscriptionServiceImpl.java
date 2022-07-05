package com.sitech.health.service.impl;

import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.DeviceStatus;
import com.sitech.health.domain.FitnessData;
import com.sitech.health.domain.Subscription;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.mapper.SubscriptionMapper;
import com.sitech.health.repository.FitnessDataRepository;
import com.sitech.health.repository.SubscriptionRepository;
import com.sitech.health.service.SubscriptionService;
import com.sitech.health.util.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private FitnessDataRepository fitnessDataRepository;
    private String lang;
    @Autowired
    private Translator translator;


    @Override
    public ResponseEntity<com.sitech.dbs.health_service.api.service.v2.model.Subscription> subscribe(UserContextDto userContextLite, String requestedLanguage, com.sitech.dbs.health_service.api.service.v2.model.Subscription subscription) {
        Subscription entity = SubscriptionMapper.INSTANCE.dtoToEntity(subscription);
        entity.setBankId(userContextLite.getBankId());
        entity.setCustomerId(userContextLite.getCustomerId());
        Subscription savedSubscription = subscriptionRepository.save(entity);
        addInitFitnessData(savedSubscription);
        return ResponseEntity.status(HttpStatus.OK).body(SubscriptionMapper.INSTANCE.entityToDto(savedSubscription));
    }

    @Override
    public ResponseEntity<List<com.sitech.dbs.health_service.api.service.v2.model.Subscription>> getCustomerSubscription(UserContextDto userContextLite, String requestedLanguage) {
        List<Subscription> subscriptionList = subscriptionRepository.findByCustomerId(userContextLite.getCustomerId());
        return ResponseEntity.status(HttpStatus.OK).body(SubscriptionMapper.INSTANCE.entityToDtoِList(subscriptionList));
    }

    private void addInitFitnessData(Subscription savedSubscription) {
        FitnessData fitnessData = new FitnessData();
        fitnessData.setNumberOfSteps("0");
        fitnessData.setBankId(savedSubscription.getBankId());
        fitnessData.setCustomerId(savedSubscription.getCustomerId());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        fitnessData.setFromDate(dateFormat.format(date));
        fitnessData.setToDate(dateFormat.format(date));
        fitnessDataRepository.save(fitnessData);
    }

    public ResponseEntity<com.sitech.dbs.health_service.api.service.v2.model.Subscription> activateDeactivateDevice(com.sitech.dbs.health_service.api.service.v2.model.Subscription subscription, boolean activate) {
        Subscription entity = subscriptionRepository.findById(subscription.getId().toString()).get();
        if (Objects.nonNull(entity)) {
            if (activate) {
                entity.setDeviceStatus(DeviceStatus.ACTIVE);
            } else {
                entity.setDeviceStatus(DeviceStatus.DEACTIVATE);
            }
            Subscription savedEntity = subscriptionRepository.save(entity);
            return ResponseEntity.status(HttpStatus.OK).body(SubscriptionMapper.INSTANCE.entityToDto(savedEntity));
        }
        throw new GenericErrorException(String.format("Device %s Not Valid.", subscription.getDeviceId()));
    }
}



//
//        //////////////////////////
//
//        ResponseEntity<List<com.sitech.dbs.health_service.api.service.v2.model.Subscription>> subLst = subscriptionService.getCustomerSubscription(userContextLite, requestedLanguage);
//        List<com.sitech.dbs.health_service.api.service.v2.model.Subscription> lst = subLst.getBody();
//        Predicate<com.sitech.dbs.health_service.api.service.v2.model.Subscription> isDeviceIdValid = subscript -> deviceActivation.getDeviceId().equals(subscript.getDeviceId());
//
//        Predicate<com.sitech.dbs.health_service.api.service.v2.model.Subscription> isDeviceActive = subscript -> subscript.getDeviceStatus().getValue() == DeviceStatus.ACTIVE.name();
//
//        com.sitech.dbs.health_service.api.service.v2.model.Subscription subscription = lst.stream()
//                .filter(isDeviceIdValid)
//                .findAny()
//                .orElseThrow(() -> new GenericErrorException(String.format("Device %s Not Valid.", deviceActivation.getDeviceId())));
//
//
//        List<com.sitech.dbs.health_service.api.service.v2.model.Subscription> deActivateLst = lst.stream()
//                .filter(subscript -> subscript.getDeviceStatus().getValue() != DeviceStatus.ACTIVE.name())
//                .collect(Collectors.toList());
//
//        //////////////////////////
//
//        return SubscriptionMapper.INSTANCE.entityToDto(savedEntity);
//            return null;