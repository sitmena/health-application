package com.sitech.health.service.impl;

import com.sitech.health.commons.ServiceConstants;
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
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
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
//
//        List<Subscription> customerSubscriptionList = getCustomerSubscriptions(userContextLite ,requestedLanguage);
//
//        if(!customerSubscriptionList.isEmpty()){
//            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.DEVICE_NOT_ACTIVATED_ERROR, "device.not.activated.error.title", "device.not.activated.error.message", this.lang));
//        }

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


    public List<Subscription> getCustomerSubscriptions(UserContextDto userContextLite, String requestedLanguage) {
        return subscriptionRepository.findByCustomerId(userContextLite.getCustomerId());
    }

    private void addInitFitnessData(Subscription savedSubscription) {
        FitnessData fitnessData = new FitnessData();
        fitnessData.setNumberOfSteps("0");
        fitnessData.setBankId(savedSubscription.getBankId());
        fitnessData.setCustomerId(savedSubscription.getCustomerId());
        fitnessData.setDeviceId(savedSubscription.getDeviceId());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        fitnessData.setFromDate(dateFormat.format(date));
        fitnessData.setToDate(dateFormat.format(date));
        fitnessDataRepository.save(fitnessData);
    }

    public ResponseEntity<com.sitech.dbs.health_service.api.service.v2.model.Subscription> activateDeactivateDevice(com.sitech.dbs.health_service.api.service.v2.model.Subscription subscription, boolean activate) {
        Subscription entity = subscriptionRepository.findById(subscription.getId()).get();
        if (Objects.nonNull(entity)) {
            if (activate) {
                entity.setDeviceStatus(DeviceStatus.ACTIVE);
            } else {
                entity.setDeviceStatus(DeviceStatus.DEACTIVATE);
            }
            Subscription savedEntity = subscriptionRepository.save(entity);
            return ResponseEntity.status(HttpStatus.OK).body(SubscriptionMapper.INSTANCE.entityToDto(savedEntity));
        }
        throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.NOT_VALID_DEVICE_ERROR, "not.valid.device.error.title", "not.valid.device.error.message", this.lang));
    }
}