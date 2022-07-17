package com.sitech.health.service.impl;

import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.FitnessDataEntity;
import com.sitech.health.repository.FitnessDataRepository;
import com.sitech.health.repository.RedeemConfigurationRepository;
import com.sitech.health.service.CustomerSubscriptionService;
import com.sitech.health.service.FitnessDataService;
import com.sitech.health.util.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FitnessDataServiceImpl implements FitnessDataService {

    @Autowired
    private FitnessDataRepository fitnessRepository;
    @Autowired
    private RedeemConfigurationRepository redeemRepository;
    @Autowired
    private Translator translator;
    @Autowired
    private CustomerSubscriptionService subscriptionService;
    private String lang;
    private static final Logger log = LoggerFactory.getLogger(FitnessDataServiceImpl.class);


    @Override
    public List<FitnessDataEntity> getFitnessByCustomerId(String customerId) {
        List<FitnessDataEntity> result = fitnessRepository.findByCustomerId(customerId);
        return result;
    }


    @Override
    public FitnessDataEntity findFirstByCustomerIdOrderByCreatedAt(String customerId) {
        return fitnessRepository.findFirstByCustomerIdOrderByCreatedAt(customerId);
    }

    @Override
    public FitnessDataEntity findById(UUID fitnessId) {
        return fitnessRepository.findById(fitnessId).get();
    }

    @Override
    public FitnessDataEntity findFirstByCustomerIdAndDeviceIdOrderByCreatedAtDesc(String customerId, UUID deviceId) {
        FitnessDataEntity resultFitnessData = fitnessRepository.findFirstByCustomerIdAndDeviceIdOrderByCreatedAtDesc(customerId, deviceId);
        if (Objects.nonNull(resultFitnessData)) {
            return resultFitnessData;
        } else {
            return new FitnessDataEntity();
        }
    }

    @Override
    public FitnessDataEntity postFitness(UserContextDto userContextLite, String requestedLanguage, FitnessDataEntity fitnessDataEntity) {
            this.lang = requestedLanguage;
            fitnessDataEntity.setBankId(userContextLite.getBankId());
            fitnessDataEntity.setCustomerId(userContextLite.getCustomerId());
            FitnessDataEntity savedFitnessItem = fitnessRepository.save(fitnessDataEntity);
            return savedFitnessItem;
    }

}
