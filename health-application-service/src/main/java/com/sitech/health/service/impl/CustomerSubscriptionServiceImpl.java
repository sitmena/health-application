package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.DeviceInfo;
import com.sitech.health.commons.ServiceConstants;
import com.sitech.health.domain.DeviceStatus;
import com.sitech.health.domain.FitnessDataEntity;
import com.sitech.health.domain.SubscriptionEntity;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.mapper.CustomerSubscriptionMapper;
import com.sitech.health.mapper.FitnessDataMapper;
import com.sitech.health.repository.CustomerSubscriptionRepository;
import com.sitech.health.service.CustomerDataService;
import com.sitech.health.service.CustomerSubscriptionService;
import com.sitech.health.service.secuirty.SecurityContextHelper;
import com.sitech.health.util.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class CustomerSubscriptionServiceImpl implements CustomerSubscriptionService {

    @Autowired
    private CustomerSubscriptionRepository customerSubscriptionRepository;
    private List<SubscriptionEntity> customerSubscriptionList;
    private Translator translator;
    private static final Logger log = LoggerFactory.getLogger(CustomerSubscriptionServiceImpl.class);
    @Autowired
    private CustomerDataService customerDataService;

    private SecurityContextHelper securityContextHelper;

    public CustomerSubscriptionServiceImpl(SecurityContextHelper securityContextHelper, Translator translator) {
        this.securityContextHelper = securityContextHelper;
        this.translator = translator;
    }

    @Override
    public List<SubscriptionEntity> getCustomerSubscriptions() {
        String customerId = securityContextHelper.getAuthJTWClaim(ServiceConstants.CUSTOMER_ID_CLAIM);
        return customerSubscriptionRepository.findByCustomerIdOrderByDeviceStatus(customerId);
    }

    @Override
    public List<SubscriptionEntity> getCustomerSubscriptionsAndDeviceStatus() {
        String customerId = securityContextHelper.getAuthJTWClaim(ServiceConstants.CUSTOMER_ID_CLAIM);
        return customerSubscriptionRepository.findFirstByCustomerIdAndDeviceStatusOrderByCreatedAt(customerId, DeviceStatus.ACTIVE);
    }

    @Override
    public boolean isCustomerSubscribed() {
        customerSubscriptionList = getCustomerSubscriptions();
        return !customerSubscriptionList.isEmpty();
    }

    @Override
    public SubscriptionEntity addCustomerSubscription(DeviceInfo deviceInfo) {
        if (!isCustomerSubscribed()) {
            SubscriptionEntity activatedEntity = CustomerSubscriptionMapper.INSTANCE.dtoToEntity(deviceInfo);
            activatedEntity.setDeviceStatus(DeviceStatus.ACTIVE);
            activatedEntity.setBankId(securityContextHelper.getAuthJTWClaim(ServiceConstants.BANK_ID_CLAIM));
            activatedEntity.setCustomerId(securityContextHelper.getAuthJTWClaim(ServiceConstants.CUSTOMER_ID_CLAIM));
            return customerSubscriptionRepository.save(activatedEntity);
        }
        userAlreadySubscribedError();
        return null;
    }

    @Override
    public boolean isDeviceActive(DeviceInfo deviceInfo) {
        if (isCustomerSubscribed()) {
            Predicate<SubscriptionEntity> isDeviceIdValid = subscript -> deviceInfo.getDeviceId().equals(subscript.getDeviceId());
            Optional<SubscriptionEntity> subscribed = customerSubscriptionList.stream().filter(isDeviceIdValid).findAny();
            if (subscribed.isPresent() && subscribed.get().getDeviceStatus().equals(DeviceStatus.ACTIVE)) {
                return true;
            }
        }
        deviceNotActivatedError();
        return false;
    }

    private void deactivateCustomerDevices() {
        log.info("-------------------- Start deactivate Customer Devices ---------------------");
        if (!customerSubscriptionList.isEmpty()) {
            for (SubscriptionEntity entity : customerSubscriptionList) {
                if (!entity.getDeviceId().equals(activatedEntity.getDeviceId())) {
                    log.info("Deactivate device [ {} ] ", entity.getDeviceId());
                    entity.setDeviceStatus(DeviceStatus.DEACTIVATE);
                    customerSubscriptionRepository.save(entity);
                }
            }
        }
    }

    private SubscriptionEntity activatedEntity;

    @Override
    public SubscriptionEntity activateDevice(DeviceInfo deviceInfo) {

        if (isCustomerSubscribed()) {
            Predicate<SubscriptionEntity> isDeviceIdValid = subscript -> deviceInfo.getDeviceId().equals(subscript.getDeviceId());
            Optional<SubscriptionEntity> subscribed = customerSubscriptionList.stream().filter(isDeviceIdValid).findAny();
            if (subscribed.isPresent()) {
                subscribed.get().setDeviceStatus(DeviceStatus.ACTIVE);
                activatedEntity = customerSubscriptionRepository.save(subscribed.get());
            } else {
                SubscriptionEntity activatedEntity1 = CustomerSubscriptionMapper.INSTANCE.dtoToEntity(deviceInfo);
                activatedEntity1.setDeviceStatus(DeviceStatus.ACTIVE);
                activatedEntity1.setBankId(securityContextHelper.getAuthJTWClaim(ServiceConstants.BANK_ID_CLAIM));
                activatedEntity1.setCustomerId(securityContextHelper.getAuthJTWClaim(ServiceConstants.CUSTOMER_ID_CLAIM));
                activatedEntity = customerSubscriptionRepository.save(activatedEntity1);
            }

            moveOldStepsToNewDeviceRecord(activatedEntity);
        } else {
            log.info("Customer Not SubScriped ....");
        }
        return null;
    }

    public void deviceNotActivatedError() {
        throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.DEVICE_NOT_ACTIVATED_ERROR, "device.not.activated.error.title", "device.not.activated.error.message", "EN"));
    }

    public void userAlreadySubscribedError() {
        throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.USER_ALREADY_SUBSCRIBED_ERROR, "user.already.subscribed.error.title", "user.already.subscribed.error.message", "EN"));
    }

    private void moveOldStepsToNewDeviceRecord(SubscriptionEntity entity) {

        deactivateCustomerDevices();
        FitnessDataEntity oldEntity = customerDataService.getLastCustomerRecord();
        if (Objects.nonNull(oldEntity)) {
            FitnessDataEntity updatedData = populateReminderFitnessDataObject(oldEntity);
            FitnessDataEntity x = customerDataService.addFitnessData(FitnessDataMapper.INSTANCE.entityToDto(updatedData));
        }
    }

    private FitnessDataEntity populateReminderFitnessDataObject(FitnessDataEntity fitnessData) {
        FitnessDataEntity updatedObject = new FitnessDataEntity();
        updatedObject.setNumberOfSteps(fitnessData.getNumberOfSteps());
        updatedObject.setFromDate(Instant.now().getEpochSecond());
        updatedObject.setToDate(Instant.now().getEpochSecond());
        updatedObject.setDeviceId(activatedEntity.getDeviceId());
        updatedObject.setCustomerId(fitnessData.getCustomerId());
        updatedObject.setBankId(fitnessData.getBankId());
        return updatedObject;
    }

}