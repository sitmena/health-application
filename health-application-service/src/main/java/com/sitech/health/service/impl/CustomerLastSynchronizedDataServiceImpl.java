package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.HealthData;
import com.sitech.dbs.health_service.api.service.v2.model.Subscription;
import com.sitech.health.commons.ServiceConstants;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.FitnessDataEntity;
import com.sitech.health.domain.RedeemConfigurationEntity;
import com.sitech.health.domain.SubscriptionEntity;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.mapper.FitnessDataMapper;
import com.sitech.health.mapper.RedeemConfigurationMapper;
import com.sitech.health.service.CustomerLastSynchronizedDataService;
import com.sitech.health.service.CustomerSubscriptionService;
import com.sitech.health.service.FitnessDataService;
import com.sitech.health.service.RedeemConfigurationService;
import com.sitech.health.util.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;


@Service
public class CustomerLastSynchronizedDataServiceImpl implements CustomerLastSynchronizedDataService {


    @Autowired
    private FitnessDataService fitnessDataService;
    @Autowired
    private CustomerSubscriptionService subscriptionService;
    @Autowired
    private RedeemConfigurationService redeemConfigurationService;
    @Autowired
    private Translator translator;
    private String lang;
    private static final Logger log = LoggerFactory.getLogger(CustomerLastSynchronizedDataServiceImpl.class);

    @Override
    public HealthData getCustomerLastSynchronizedData(UserContextDto userContextLite, String requestedLanguage, UUID deviceId, boolean activateDevice) {
        this.lang = requestedLanguage;
        Subscription subscription1 = new Subscription();
        subscription1.setDeviceId(deviceId);
        SubscriptionEntity isRequestedDeviceActive = subscriptionService.subscribeCustomer(userContextLite, this.lang, subscription1, Boolean.valueOf(activateDevice));
        if (Objects.nonNull(isRequestedDeviceActive)) {
            FitnessDataEntity fitnessDataEntity = fitnessDataService.findFirstByCustomerIdAndDeviceIdOrderByCreatedAtDesc(userContextLite.getCustomerId(), deviceId);
            return populateHealthData(fitnessDataEntity);
        }
        throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.DEVICE_NOT_ACTIVATED_ERROR, "device.not.activated.error.title", "device.not.activated.error.message", this.lang));
    }

    private HealthData populateHealthData(FitnessDataEntity fitnessDataEntity) {
        RedeemConfigurationEntity redeemConfiguration = redeemConfigurationService.getRedeemConfigurationByBankId(fitnessDataEntity.getBankId());
        HealthData healthData = new HealthData();
        healthData.setFitnnessData(FitnessDataMapper.INSTANCE.entityToDto(fitnessDataEntity));
        healthData.setRedeemConfiguration(RedeemConfigurationMapper.INSTANCE.entityToDto(redeemConfiguration));
        return healthData;
    }

    @Override
    public FitnessDataEntity getLastCustomerRecord(UserContextDto userContextLite, String requestedLanguage) {
        FitnessDataEntity fitnessDataEntity = fitnessDataService.findFirstByCustomerIdOrderByCreatedAt(userContextLite.getCustomerId());
        return fitnessDataEntity;
    }
}
