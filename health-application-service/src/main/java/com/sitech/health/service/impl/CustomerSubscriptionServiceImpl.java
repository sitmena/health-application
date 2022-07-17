package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.Subscription;
import com.sitech.health.commons.ServiceConstants;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.DeviceStatus;
import com.sitech.health.domain.FitnessDataEntity;
import com.sitech.health.domain.SubscriptionEntity;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.mapper.CustomerSubscriptionMapper;
import com.sitech.health.repository.CustomerSubscriptionRepository;
import com.sitech.health.service.CustomerLastSynchronizedDataService;
import com.sitech.health.service.CustomerSubscriptionService;
import com.sitech.health.service.FitnessDataService;
import com.sitech.health.util.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class CustomerSubscriptionServiceImpl implements CustomerSubscriptionService {

    @Autowired
    private CustomerSubscriptionRepository customerSubscriptionRepository;
    @Autowired
    private Translator translator;
    @Autowired
    private CustomerLastSynchronizedDataService customerLastSynchronizedDataService;
    @Autowired
    private FitnessDataService fitnessDataService;
    private String lang;
    private SubscriptionEntity updatedEntity;
    private static final Logger log = LoggerFactory.getLogger(CustomerSubscriptionServiceImpl.class);
    private List<SubscriptionEntity> customerSubscriptionList;

    @Override
    public SubscriptionEntity subscribeCustomer(UserContextDto userContextLite, String requestedLanguage, Subscription subscription, boolean activateThisDevice) {
        if (Objects.isNull(subscription)) {
            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.DEVICE_NOT_ACTIVATED_ERROR, "device.not.activated.error.title", "device.not.activated.error.message", this.lang));
        }
        boolean isCustomerSubscribed = isDeviceSubscribed(userContextLite, subscription, requestedLanguage);
        if (!isCustomerSubscribed && customerSubscriptionList.isEmpty()) {
            return addCustomerSubscription(userContextLite, subscription, requestedLanguage);
        }
        if (activateThisDevice) {
            activateDevice(userContextLite, subscription, requestedLanguage);
        } else {
            isDeviceActive(userContextLite, subscription, requestedLanguage);
        }
        return CustomerSubscriptionMapper.INSTANCE.dtoToEntity(subscription);
    }


    @Override
    public SubscriptionEntity addCustomerSubscription(UserContextDto userContextLite, Subscription subscription, String requestedLanguage) {
        SubscriptionEntity entity = CustomerSubscriptionMapper.INSTANCE.dtoToEntity(subscription);
        entity.setDeviceStatus(DeviceStatus.ACTIVE);
        entity.setBankId(userContextLite.getBankId());
        entity.setCustomerId(userContextLite.getCustomerId());
        SubscriptionEntity savedEntity = customerSubscriptionRepository.save(entity);
        log.info(".... Add New Customer Subscription");
        return savedEntity;
    }

    @Override
    public SubscriptionEntity updateCustomerSubscription(UserContextDto userContextLite, SubscriptionEntity subscriptionEntity) {
        subscriptionEntity.setBankId(userContextLite.getBankId());
        subscriptionEntity.setCustomerId(userContextLite.getCustomerId());
        return customerSubscriptionRepository.save(subscriptionEntity);
    }


    @Override
    public List<SubscriptionEntity> getCustomerSubscriptions(UserContextDto userContextLite, String requestedLanguage) {
        List<SubscriptionEntity> lst = customerSubscriptionRepository.findByCustomerId(userContextLite.getCustomerId());
        return lst;
    }


    /*
     * This Method will Only validate if the device is subscribed.
     */
    @Override
    public boolean isDeviceSubscribed(UserContextDto userContextLite, Subscription subscription, String requestedLanguage) {
        customerSubscriptionList = getCustomerSubscriptions(userContextLite, requestedLanguage);
        Optional<SubscriptionEntity> subscribed;
        if (!customerSubscriptionList.isEmpty()) {
            Predicate<SubscriptionEntity> isDeviceIdValid = subscript -> subscription.getDeviceId().equals(subscript.getDeviceId());
            subscribed = customerSubscriptionList.stream().filter(isDeviceIdValid).findAny();
            if (subscribed.isPresent()) {
                log.info("... Device Subscribed");
                return true;
            }
        }
        log.info("... Device Not Subscribed");
        return false;
    }

    /*
     * This Method will validate if the device is subscribed and activated.
     */
    @Override
    public boolean isDeviceActive(UserContextDto userContextLite, Subscription subscription, String requestedLanguage) {
        List<SubscriptionEntity> customerSubscriptionList = getCustomerSubscriptions(userContextLite, requestedLanguage);
        Optional<SubscriptionEntity> subscribed;
        if (!customerSubscriptionList.isEmpty()) {
            Predicate<SubscriptionEntity> isDeviceIdValid = subscript -> subscription.getDeviceId().equals(subscript.getDeviceId());
            subscribed = customerSubscriptionList.stream().filter(isDeviceIdValid).findAny();
            if (subscribed.isPresent() && subscribed.get().getDeviceStatus().equals(DeviceStatus.ACTIVE)) {
                return true;
            } else {
                throw new GenericErrorException("Device Not Activated");
            }
        }
        return false;
    }

    private void deActivateOtherDevices(UserContextDto userContextLite, String requestedLanguage, SubscriptionEntity subscriptionEntity) {
        List<SubscriptionEntity> customerSubscriptionList = getCustomerSubscriptions(userContextLite, requestedLanguage);
        for (SubscriptionEntity entity : customerSubscriptionList) {
            if (!entity.getDeviceId().equals(subscriptionEntity.getDeviceId())) {
                entity.setDeviceStatus(DeviceStatus.DEACTIVATE);
                updatedEntity = updateCustomerSubscription(userContextLite, entity);
            }
        }
    }

    @Override
    public SubscriptionEntity activateDevice(UserContextDto userContextLite, Subscription subscription, String requestedLanguage) {
        log.info(".... Start Device Activation with data {}" , subscription.toString());
        SubscriptionEntity customerRecord = customerSubscriptionRepository.findByCustomerIdAndDeviceId(userContextLite.getCustomerId(), subscription.getDeviceId());
        if (Objects.nonNull(customerRecord)) {
            List<SubscriptionEntity> customerSubscriptionList = getCustomerSubscriptions(userContextLite, requestedLanguage);
            for (SubscriptionEntity entity : customerSubscriptionList) {
                if (entity.getDeviceId().equals(subscription.getDeviceId())) {
                    entity.setDeviceStatus(DeviceStatus.ACTIVE);
                    entity.setDeviceId(subscription.getDeviceId());
//                moveOldStepsToNewDeviceRecord(userContextLite, requestedLanguage, entity);
                } else {
                    entity.setDeviceStatus(DeviceStatus.DEACTIVATE);
                }
                updatedEntity = updateCustomerSubscription(userContextLite, entity);
            }
        } else {
            addCustomerSubscription(userContextLite, subscription, requestedLanguage);
        }
        return updatedEntity;
    }


    private void moveOldStepsToNewDeviceRecord(UserContextDto userContextLite, String requestedLanguage, SubscriptionEntity entity) {
        FitnessDataEntity oldEntity = customerLastSynchronizedDataService.getLastCustomerRecord(userContextLite, requestedLanguage);
        if (Objects.nonNull(oldEntity)) {
            oldEntity.setDeviceId(entity.getDeviceId());
            FitnessDataEntity updatedData = populateReminderFitnessDataObject(oldEntity);
            FitnessDataEntity x = fitnessDataService.postFitness(userContextLite, requestedLanguage, updatedData);
        }
    }

    private FitnessDataEntity populateReminderFitnessDataObject(FitnessDataEntity fitnessData) {
        FitnessDataEntity updatedObject = new FitnessDataEntity();
        updatedObject.setNumberOfSteps(fitnessData.getNumberOfSteps());
        updatedObject.setFromDate(OffsetDateTime.now());
        updatedObject.setToDate(OffsetDateTime.now());
        updatedObject.setDeviceId(fitnessData.getDeviceId());
        updatedObject.setCustomerId(fitnessData.getCustomerId());
        updatedObject.setBankId(fitnessData.getBankId());
        return updatedObject;
    }
}