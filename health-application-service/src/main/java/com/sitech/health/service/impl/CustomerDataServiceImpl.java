package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.DeviceInfo;
import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.dbs.health_service.api.service.v2.model.HealthData;
import com.sitech.health.commons.ServiceConstants;
import com.sitech.health.domain.FitnessDataEntity;
import com.sitech.health.domain.RedeemConfigurationEntity;
import com.sitech.health.mapper.FitnessDataMapper;
import com.sitech.health.mapper.RedeemConfigurationMapper;
import com.sitech.health.repository.FitnessDataRepository;
import com.sitech.health.service.CustomerDataService;
import com.sitech.health.service.CustomerSubscriptionService;
import com.sitech.health.service.RedeemConfigurationService;
import com.sitech.health.service.secuirty.SecurityContextHelper;
import com.sitech.health.util.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class CustomerDataServiceImpl implements CustomerDataService {

    @Autowired
    private CustomerSubscriptionService subscriptionService;
    @Autowired
    private RedeemConfigurationService redeemConfigurationService;
    @Autowired
    private FitnessDataRepository fitnessRepository;

    private SecurityContextHelper securityContextHelper;
    private Translator translator;
    private static final Logger log = LoggerFactory.getLogger(CustomerDataServiceImpl.class);

    public CustomerDataServiceImpl(SecurityContextHelper securityContextHelper, Translator translator) {
        this.securityContextHelper = securityContextHelper;
        this.translator = translator;
    }

    @Override
    public HealthData getCustomerLastActiveSynchronizedData(UUID deviceId) {
        DeviceInfo info = new DeviceInfo();
        info.setDeviceId(deviceId);
        if (subscriptionService.isDeviceActive(info)) {
            return populateHealthData(getLastCustomerRecord());
        }
        return null;
    }

    @Override
    public FitnessDataEntity getLastCustomerRecord() {
        FitnessDataEntity resultFitnessData = fitnessRepository.findFirstByCustomerIdOrderByCreatedAtDesc(securityContextHelper.getAuthJTWClaim(ServiceConstants.CUSTOMER_ID_CLAIM));
        if (Objects.nonNull(resultFitnessData)) {
            return resultFitnessData;
        } else {
            return new FitnessDataEntity();
        }
    }

    @Override
    public FitnessDataEntity addFitnessData(FitnessData fitnessData) {
        DeviceInfo info = new DeviceInfo();
        info.setDeviceId(fitnessData.getDeviceId());
        if (subscriptionService.isDeviceActive(info)) {
            FitnessDataEntity entity = FitnessDataMapper.INSTANCE.dtoToEntity(fitnessData);
            entity.setBankId(securityContextHelper.getAuthJTWClaim(ServiceConstants.BANK_ID_CLAIM));
            entity.setCustomerId(securityContextHelper.getAuthJTWClaim(ServiceConstants.CUSTOMER_ID_CLAIM));
            FitnessDataEntity savedFitnessItem = fitnessRepository.save(entity);
            return savedFitnessItem;
        }
        return null;
    }


    private HealthData populateHealthData(FitnessDataEntity fitnessDataEntity) {
        RedeemConfigurationEntity redeemConfiguration = redeemConfigurationService.getRedeemConfigurationByBankId(fitnessDataEntity.getBankId());
        HealthData healthData = new HealthData();
        healthData.setFitnnessData(FitnessDataMapper.INSTANCE.entityToDto(fitnessDataEntity));
        healthData.setRedeemConfiguration(RedeemConfigurationMapper.INSTANCE.entityToDto(redeemConfiguration));
        return healthData;
    }
}
