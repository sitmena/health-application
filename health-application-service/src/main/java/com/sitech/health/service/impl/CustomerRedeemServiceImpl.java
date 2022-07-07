package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.HealthData;
import com.sitech.dbs.health_service.api.service.v2.model.RedeemConfiguration;
import com.sitech.health.commons.ServiceConstants;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.CustomerRedeems;
import com.sitech.health.domain.FitnessData;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.mapper.FitnessDataMapper;
import com.sitech.health.repository.CustomerRedeemsRepository;
import com.sitech.health.service.CustomerRedeemService;
import com.sitech.health.service.FitnessDataService;
import com.sitech.health.service.RedeemConfigurationService;
import com.sitech.health.util.Translator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;


@Service
@Slf4j
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

//    public CustomerRedeemServiceImpl(FitnessDataService fitnessDataService, RedeemConfigurationService redeemConfigurationService, CustomerRedeemsRepository customerRedeemsRepository) {
//        this.fitnessDataService = fitnessDataService;
//        this.redeemConfigurationService = redeemConfigurationService;
//        this.customerRedeemsRepository = customerRedeemsRepository;
//    }

    @Override
    public ResponseEntity<HealthData> doRedeem(UserContextDto userContextLite, String requestedLanguage, com.sitech.dbs.health_service.api.service.v2.model.FitnessData fitnessItem) {
        this.userContextLite = userContextLite;
        this.lang = requestedLanguage;

        ResponseEntity<HealthData> data = fitnessDataService.getFitnessByCustomerId(userContextLite.getCustomerId());
        if (!Objects.isNull(data.getBody())) {
            HealthData result = calculatePoint(FitnessDataMapper.INSTANCE.dtoToEntity(data.getBody().getFitnnessData()));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.ERROR_IN_PERSIST_DATA, "jpa.persist.data.error.title", "jpa.persist.data.error.message", this.lang));
        }

    }

    private HealthData calculatePoint(FitnessData fitnessData) {
        RedeemConfiguration redeemConfiguration = redeemConfigurationService.getRedeemByBankId(this.userContextLite.getBankId());

        if (redeemConfiguration.getCriteriaName().equals(ServiceConstants.STEPS_CRITERIA_NAME)) {
            BigDecimal numberOfSteps = new BigDecimal(fitnessData.getNumberOfSteps());
            BigDecimal redeemWeight = new BigDecimal(redeemConfiguration.getWeight());
            BigDecimal redeemEquivalencePoints = new BigDecimal(redeemConfiguration.getEquivalencePoints());
            BigDecimal totalPoints = (numberOfSteps.divide(redeemWeight)).multiply(redeemEquivalencePoints);
            int validPointToRedeem = totalPoints.intValue();
            int x = numberOfSteps.divide(redeemWeight).intValue();
            BigDecimal totalStepsToBeRedeemed =  redeemWeight.multiply(BigDecimal.valueOf( x ));
            BigDecimal reminderSteps = numberOfSteps.subtract( totalStepsToBeRedeemed);

            if (isEligibleToRedeem(validPointToRedeem, redeemConfiguration.getMinimumPointsToBeRedeemed(), redeemConfiguration.getMaximumPointsToBeRedeemed())) {
                CustomerRedeems customerRedeems = new CustomerRedeems();
                customerRedeems.setCustomerId(this.userContextLite.getCustomerId());
                customerRedeems.setBankId(this.userContextLite.getBankId());
                customerRedeems.setNumberOfPoints(String.valueOf(validPointToRedeem));
                customerRedeems.setTotalRedeemedSteps(String.valueOf(totalStepsToBeRedeemed));
                customerRedeems.setDeviceId(fitnessData.getDeviceId());
                makeRedeem(customerRedeems);
                fitnessData.setNumberOfSteps(String.valueOf(reminderSteps));
                return addReminderRecord(fitnessData);
            } else {
                throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.ERROR_REDEEM_CONVERTER, "redeem.converter.data.error.title", "redeem.converter.data.error.message", this.lang));
            }
        }
        throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.OPERATION_NOT_FOUND_ERROR, "operation.not.found.error.title", "operation.not.found.error.message", this.lang));
    }

    private HealthData addReminderRecord(FitnessData fitnessData) {
        fitnessData.setId(null);
        HealthData healthData = fitnessDataService.postFitness(this.userContextLite, this.lang, FitnessDataMapper.INSTANCE.entityToDto(fitnessData)).getBody();
        return healthData;
    }

    private void makeRedeem(CustomerRedeems customerRedeems) {
        customerRedeemsRepository.save(customerRedeems);
    }

    private boolean isEligibleToRedeem(int numberOfPoint, int min, int max) {
        log.info(" [ isEligibleToRedeem ] >> NumberOfPoint = {} -- Min = {} -- Max = {}" , numberOfPoint , min , max);
        return ((numberOfPoint >= min) && (numberOfPoint <= max));
    }
}
