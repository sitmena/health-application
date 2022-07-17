package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.dbs.health_service.api.service.v2.model.HealthData;
import com.sitech.dbs.health_service.api.service.v2.model.Subscription;
import com.sitech.health.commons.ServiceConstants;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.CustomerRedeemsEntity;
import com.sitech.health.domain.FitnessDataEntity;
import com.sitech.health.domain.RedeemConfigurationEntity;
import com.sitech.health.domain.SubscriptionEntity;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.mapper.FitnessDataMapper;
import com.sitech.health.repository.CustomerRedeemsRepository;
import com.sitech.health.service.*;
import com.sitech.health.util.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;


@Service
public class CustomerRedeemServiceImpl implements CustomerRedeemService {

    @Autowired
    private FitnessDataService fitnessDataService;
    @Autowired
    private RedeemConfigurationService redeemConfigurationService;
    @Autowired
    private CustomerRedeemsRepository customerRedeemsRepository;
    private UserContextDto userContextLite;
    private String lang;
    @Autowired
    private Translator translator;
    @Autowired
    private CustomerSubscriptionService subscriptionService;
    @Autowired
    private CustomerLastSynchronizedDataService customerLastSynchronizedDataService;
    private static final Logger log = LoggerFactory.getLogger(CustomerRedeemServiceImpl.class);

    @Override
    public FitnessDataEntity doRedeem(UserContextDto userContextLite, String requestedLanguage, FitnessData fitnessItem) {
        this.userContextLite = userContextLite;
        this.lang = requestedLanguage;
        Subscription subscription = new Subscription();
        subscription.setDeviceId(fitnessItem.getDeviceId());
        SubscriptionEntity isRequestedDeviceActive = subscriptionService.subscribeCustomer(userContextLite, requestedLanguage, subscription, Boolean.FALSE);
        if (Objects.nonNull(isRequestedDeviceActive)) {
            FitnessDataEntity lastSynchronizedData = fitnessDataService.postFitness(userContextLite, requestedLanguage, FitnessDataMapper.INSTANCE.dtoToEntity(fitnessItem));
            if (!Objects.isNull(lastSynchronizedData)) {
                HealthData lastData = customerLastSynchronizedDataService.getCustomerLastSynchronizedData(userContextLite,requestedLanguage,lastSynchronizedData.getDeviceId(),false);
                return calculatePoint(FitnessDataMapper.INSTANCE.dtoToEntity(lastData.getFitnnessData()));
            } else {
                throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.ERROR_IN_PERSIST_DATA, "jpa.persist.data.error.title", "jpa.persist.data.error.message", this.lang));
            }
        }
        return null;
    }

    private FitnessDataEntity calculatePoint(FitnessDataEntity fitnessData) {
        RedeemConfigurationEntity redeemConfiguration = redeemConfigurationService.getRedeemConfigurationByBankId(this.userContextLite.getBankId());
        if (redeemConfiguration.getFactor().equals(ServiceConstants.STEPS_CRITERIA_NAME)) {
            BigDecimal numberOfSteps = new BigDecimal(fitnessData.getNumberOfSteps());
            BigDecimal pointTarget = new BigDecimal(redeemConfiguration.getPointTarget());
            BigDecimal totalPoints = (numberOfSteps.divide(pointTarget));
            int validPointToRedeem = totalPoints.intValue();
            int reminderSteps = fitnessData.getNumberOfSteps() - (validPointToRedeem * redeemConfiguration.getPointTarget());
            if (isEligibleToRedeem(validPointToRedeem, redeemConfiguration.getMinimumPointsToBeRedeemed(), redeemConfiguration.getMaximumPointsToBeRedeemed())) {
                CustomerRedeemsEntity customerRedeems = new CustomerRedeemsEntity();
                customerRedeems.setCustomerId(this.userContextLite.getCustomerId());
                customerRedeems.setBankId(this.userContextLite.getBankId());
                customerRedeems.setNumberOfPoints(validPointToRedeem);
                customerRedeems.setTotalRedeemedSteps(validPointToRedeem * redeemConfiguration.getPointTarget());
                customerRedeems.setDeviceId(fitnessData.getDeviceId());
                CustomerRedeemsEntity redeemEntity = makeRedeem(customerRedeems);
                fitnessData.setNumberOfSteps(reminderSteps);
                return addReminderRecord(populateReminderFitnessDataObject(fitnessData));
            }
            if (validPointToRedeem >= redeemConfiguration.getMinimumPointsToBeRedeemed()) {
                if (validPointToRedeem >= redeemConfiguration.getMaximumPointsToBeRedeemed()) {

                }
            }
        }
        throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.OPERATION_NOT_FOUND_ERROR, "operation.not.found.error.title", "operation.not.found.error.message", this.lang));
    }

    private FitnessDataEntity populateReminderFitnessDataObject(FitnessDataEntity fitnessData) {
        FitnessDataEntity newObject = new FitnessDataEntity();
        newObject.setNumberOfSteps(fitnessData.getNumberOfSteps());
        newObject.setFromDate(OffsetDateTime.now());
        newObject.setToDate(OffsetDateTime.now());
        newObject.setDeviceId(fitnessData.getDeviceId());
        newObject.setCustomerId(fitnessData.getCustomerId());
        newObject.setBankId(fitnessData.getBankId());
        return newObject;
    }

    private FitnessDataEntity addReminderRecord(FitnessDataEntity fitnessData) {
        FitnessDataEntity healthData = fitnessDataService.postFitness(this.userContextLite, this.lang, fitnessData);
        return healthData;
    }

    private CustomerRedeemsEntity makeRedeem(CustomerRedeemsEntity customerRedeems) {
        return customerRedeemsRepository.save(customerRedeems);
    }

    private boolean isEligibleToRedeem(int numberOfPoint, int min, int max) {
        return ((numberOfPoint >= min) && (numberOfPoint <= max));
    }
}