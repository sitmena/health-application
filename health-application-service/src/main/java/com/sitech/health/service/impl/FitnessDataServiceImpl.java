package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.dbs.health_service.api.service.v2.model.HealthData;
import com.sitech.dbs.health_service.api.service.v2.model.RedeemConfiguration;
import com.sitech.dbs.health_service.api.service.v2.model.Subscription;
import com.sitech.health.commons.ServiceConstants;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.DeviceStatus;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.mapper.FitnessDataMapper;
import com.sitech.health.mapper.RedeemConfigurationMapper;
import com.sitech.health.repository.FitnessDataRepository;
import com.sitech.health.repository.RedeemConfigurationRepository;
import com.sitech.health.service.FitnessDataService;
import com.sitech.health.util.Translator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Service
@Slf4j
public class FitnessDataServiceImpl implements FitnessDataService {

    @Autowired
    private FitnessDataRepository fitnessRepository;
    @Autowired
    private RedeemConfigurationRepository redeemRepository;
    @Autowired
    private SubscriptionServiceImpl subscriptionService;
    private String lang;
    @Autowired
    private Translator translator;

    @Override
    public ResponseEntity<HealthData> getFitnessByCustomerId(String customerId) {
        com.sitech.health.domain.FitnessData resultFitnessData = fitnessRepository.findFirstByCustomerIdOrderByCreatedAtDesc(customerId);
        HealthData healthData = new HealthData();
        if (!Objects.isNull(resultFitnessData)) {
            RedeemConfiguration redeemConfig = RedeemConfigurationMapper.INSTANCE.entityToDto(redeemRepository.findByBankId(resultFitnessData.getBankId()));
            healthData.setFitnnessData(FitnessDataMapper.INSTANCE.entityToDto(resultFitnessData));
            healthData.setRedeemConfiguration(redeemConfig);
            return ResponseEntity.status(HttpStatus.OK).body(healthData);
        } else {
            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.ERROR_IN_PERSIST_DATA, "jpa.persist.data.error.title", "jpa.persist.data.error.message", this.lang));
        }
    }

    @Override
    public ResponseEntity<HealthData> findFirstByCustomerIdAndDeviceIdOrderByCreatedAtDesc(String customerId, String deviceId) {
        com.sitech.health.domain.FitnessData resultFitnessData = fitnessRepository.findFirstByCustomerIdAndDeviceIdOrderByCreatedAtDesc(customerId,deviceId);
        HealthData healthData = new HealthData();
        if (!Objects.isNull(resultFitnessData)) {
            RedeemConfiguration redeemConfig = RedeemConfigurationMapper.INSTANCE.entityToDto(redeemRepository.findByBankId(resultFitnessData.getBankId()));
            healthData.setFitnnessData(FitnessDataMapper.INSTANCE.entityToDto(resultFitnessData));
            healthData.setRedeemConfiguration(redeemConfig);
            return ResponseEntity.status(HttpStatus.OK).body(healthData);
        } else {
            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.ERROR_IN_PERSIST_DATA, "jpa.persist.data.error.title", "jpa.persist.data.error.message", this.lang));
        }
    }

    @Override
    public ResponseEntity<HealthData> postFitness(UserContextDto userContextLite, String requestedLanguage, FitnessData fitnessData) {
        this.lang = requestedLanguage;
        if (!Objects.isNull(fitnessData)) {
            isDeviceActive(userContextLite, requestedLanguage, fitnessData);
            com.sitech.health.domain.FitnessData fitnessEntity = FitnessDataMapper.INSTANCE.dtoToEntity(fitnessData);
            fitnessEntity.setBankId(userContextLite.getBankId());
            fitnessEntity.setCustomerId(userContextLite.getCustomerId());
            com.sitech.health.domain.FitnessData savedFitnessItem = fitnessRepository.save(fitnessEntity);
            HealthData healthData = new HealthData();
            RedeemConfiguration redeemConfig = RedeemConfigurationMapper.INSTANCE.entityToDto(redeemRepository.findByBankId(savedFitnessItem.getBankId()));
            healthData.setFitnnessData(FitnessDataMapper.INSTANCE.entityToDto(savedFitnessItem));
            healthData.setRedeemConfiguration(redeemConfig);
            return ResponseEntity.status(HttpStatus.OK).body(healthData);
        } else {
            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.ERROR_IN_PERSIST_DATA, "jpa.persist.data.error.title", "jpa.persist.data.error.message", this.lang));
        }
    }

    private boolean isDeviceActive(UserContextDto userContextLite, String requestedLanguage, FitnessData fitnessData) {
        ResponseEntity<List<Subscription>> subLst = subscriptionService.getCustomerSubscription(userContextLite, requestedLanguage);
        List<Subscription> lst = subLst.getBody();
        Predicate<Subscription> isDeviceIdValid = subscript -> fitnessData.getDeviceId().equals(subscript.getDeviceId());
        Predicate<Subscription> isDeviceActive = subscript -> subscript.getDeviceStatus().getValue() == DeviceStatus.ACTIVE.name();
        Subscription subscription = lst.stream()
                .filter(isDeviceIdValid.and(isDeviceActive))
                .findAny()
                .orElseThrow(() ->
                        new GenericErrorException(
                                translator.getTranslatedKey(
                                        ServiceConstants.DEVICE_NOT_ACTIVATED_ERROR, "device.not.activated.error.title", "device.not.activated.error.message", this.lang)
                        )

                );
        return true;
    }
}
