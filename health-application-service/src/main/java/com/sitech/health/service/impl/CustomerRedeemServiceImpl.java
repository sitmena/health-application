package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.DeviceInfo;
import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.health.commons.ServiceConstants;
import com.sitech.health.domain.CustomerRedeemsEntity;
import com.sitech.health.domain.FitnessDataEntity;
import com.sitech.health.domain.RedeemConfigurationEntity;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.mapper.FitnessDataMapper;
import com.sitech.health.repository.CustomerRedeemsRepository;
import com.sitech.health.service.CustomerDataService;
import com.sitech.health.service.CustomerRedeemService;
import com.sitech.health.service.CustomerSubscriptionService;
import com.sitech.health.service.RedeemConfigurationService;
import com.sitech.health.service.secuirty.SecurityContextHelper;
import com.sitech.health.util.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


@Service
public class CustomerRedeemServiceImpl implements CustomerRedeemService {

    @Autowired
    private RedeemConfigurationService redeemConfigurationService;
    @Autowired
    private CustomerRedeemsRepository customerRedeemsRepository;
    @Autowired
    private CustomerSubscriptionService subscriptionService;

    private CustomerDataService customerDataService;
    private SecurityContextHelper securityContextHelper;
    private Translator translator;
    private static final Logger log = LoggerFactory.getLogger(CustomerRedeemServiceImpl.class);


    public CustomerRedeemServiceImpl(SecurityContextHelper securityContextHelper, CustomerDataService customerDataService , Translator translator) {
        this.securityContextHelper = securityContextHelper;
        this.customerDataService = customerDataService;
        this.translator = translator;
    }

    @Override
    public FitnessDataEntity doRedeem(FitnessData fitnessData) {

        DeviceInfo info = new DeviceInfo();
        info.setDeviceId(fitnessData.getDeviceId());
            FitnessDataEntity data = customerDataService.addFitnessData(fitnessData);
            return calculatePoint(info.getDeviceId());

    }

    private FitnessDataEntity calculatePoint(UUID deviceId) {
        RedeemConfigurationEntity redeemConfiguration = redeemConfigurationService.getRedeemConfigurationByBankId(securityContextHelper.getAuthJTWClaim(ServiceConstants.BANK_ID_CLAIM));
        FitnessData fitnessData = customerDataService.getCustomerLastActiveSynchronizedData(deviceId).getFitnnessData();

        if (redeemConfiguration.getFactor().equals(ServiceConstants.STEPS_CRITERIA_NAME)) {
            BigDecimal numberOfSteps = new BigDecimal(fitnessData.getNumberOfSteps());
            BigDecimal pointTarget = new BigDecimal(redeemConfiguration.getPointTarget());
            BigDecimal totalPoints = (numberOfSteps.divide(pointTarget));

            int validPointToRedeem = totalPoints.intValue();

            // if exceed the max point we need to get the max point and convert the rest to steps
            if (validPointToRedeem > redeemConfiguration.getMaximumPointsToBeRedeemed()) {
                validPointToRedeem = redeemConfiguration.getMaximumPointsToBeRedeemed();
            }

            int reminderSteps = fitnessData.getNumberOfSteps() - (validPointToRedeem * redeemConfiguration.getPointTarget());


            if (isEligibleToRedeem(validPointToRedeem, redeemConfiguration.getMinimumPointsToBeRedeemed(), redeemConfiguration.getMaximumPointsToBeRedeemed())) {
                CustomerRedeemsEntity customerRedeems = new CustomerRedeemsEntity();
                customerRedeems.setCustomerId(securityContextHelper.getAuthJTWClaim(ServiceConstants.CUSTOMER_ID_CLAIM));
                customerRedeems.setBankId(securityContextHelper.getAuthJTWClaim(ServiceConstants.BANK_ID_CLAIM));
                customerRedeems.setNumberOfPoints(validPointToRedeem);
                customerRedeems.setTotalRedeemedSteps(validPointToRedeem * redeemConfiguration.getPointTarget());
                customerRedeems.setDeviceId(fitnessData.getDeviceId());
                CustomerRedeemsEntity redeemEntity = makeRedeem(customerRedeems);
                fitnessData.setNumberOfSteps(reminderSteps);
                return addReminderRecord(populateReminderFitnessDataObject(fitnessData));
            }

        }
        throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.OPERATION_NOT_FOUND_ERROR, "operation.not.found.error.title", "operation.not.found.error.message", "EN"));
    }

    private FitnessDataEntity populateReminderFitnessDataObject(FitnessData fitnessData) {
        FitnessDataEntity newObject = new FitnessDataEntity();
        newObject.setNumberOfSteps(fitnessData.getNumberOfSteps());
        newObject.setFromDate(Instant.now().getEpochSecond());
        newObject.setToDate(Instant.now().getEpochSecond());
        newObject.setDeviceId(fitnessData.getDeviceId());
        newObject.setCustomerId(securityContextHelper.getAuthJTWClaim(ServiceConstants.CUSTOMER_ID_CLAIM));
        newObject.setBankId(securityContextHelper.getAuthJTWClaim(ServiceConstants.BANK_ID_CLAIM));
        return newObject;
    }

    private FitnessDataEntity addReminderRecord(FitnessDataEntity fitnessData) {
        FitnessDataEntity healthData = customerDataService.addFitnessData(FitnessDataMapper.INSTANCE.entityToDto(fitnessData));
        return healthData;
    }

    private CustomerRedeemsEntity makeRedeem(CustomerRedeemsEntity customerRedeems) {
        return customerRedeemsRepository.save(customerRedeems);
    }

    private boolean isEligibleToRedeem(int numberOfPoint, int min, int max) {
        return ((numberOfPoint >= min) && (numberOfPoint <= max));
    }
}