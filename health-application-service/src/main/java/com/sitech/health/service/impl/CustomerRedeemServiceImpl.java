package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.health.commons.ServiceConstants;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.CustomerRedeems;
import com.sitech.health.domain.FitnessItem;
import com.sitech.health.domain.RedeemItem;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.mapper.FitnessMapper;
import com.sitech.health.repository.CustomerRedeemsRepository;
import com.sitech.health.service.CustomerRedeemService;
import com.sitech.health.service.FitnessService;
import com.sitech.health.service.RedeemService;
import com.sitech.health.util.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class CustomerRedeemServiceImpl implements CustomerRedeemService {

    @Autowired
    private FitnessService fitnessService;
    @Autowired
    private RedeemService redeemService;
    @Autowired
    private CustomerRedeemsRepository customerRedeemsRepository;
    private UserContextDto userContextLite;
    private String lang;
    @Autowired
    private Translator translator;

    @Override
    public ResponseEntity<FitnessData> doRedeem(UserContextDto userContextLite, String requestedLanguage, com.sitech.dbs.health_service.api.service.v2.model.FitnessItem fitnessItem) {
        this.userContextLite = userContextLite;
        this.lang = requestedLanguage;
        ResponseEntity<FitnessData> data = fitnessService.getFitnessByCustomerId(userContextLite.getCustomerId());
        if (!Objects.isNull(data.getBody())) {
            FitnessData result = calculatePoint(FitnessMapper.INSTANCE.FitnessItemToEntity(data.getBody().getFitnness().get(0)));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.ERROR_IN_PERSIST_DATA, "jpa.persist.data.error.title", "jpa.persist.data.error.message", this.lang));
        }
    }

    private FitnessData calculatePoint(FitnessItem fitnessItem) {
        RedeemItem redeem = redeemService.getRedeemByBankId(this.userContextLite.getBankId());

        if (redeem.getCriteriaName().equals(ServiceConstants.STEPS_CRITERIA_NAME)) {
            BigDecimal numberOfSteps = new BigDecimal(fitnessItem.getNumberOfSteps());
            BigDecimal redeemWeight = new BigDecimal(redeem.getWeight());
            BigDecimal redeemEquivalencePoints = new BigDecimal(redeem.getEquivalencePoints());
            BigDecimal totalPoints = numberOfSteps.divide(redeemWeight);
            int validPointToRedeem = totalPoints.intValue() ;
            BigDecimal validPointToRedeemAsDecimal = new BigDecimal(validPointToRedeem).multiply(redeemEquivalencePoints);
            BigDecimal totalStepsToBeRedeemed = BigDecimal.valueOf(validPointToRedeem).multiply(redeemWeight);
            BigDecimal reminderSteps = numberOfSteps.subtract(totalStepsToBeRedeemed);
            CustomerRedeems customerRedeems = new CustomerRedeems();
            customerRedeems.setCustomerId(this.userContextLite.getCustomerId());
            customerRedeems.setBankId(this.userContextLite.getBankId());
            customerRedeems.setNumberOfPoints(String.valueOf(validPointToRedeemAsDecimal));
            makeRedeem(customerRedeems);
            fitnessItem.setNumberOfSteps(String.valueOf(reminderSteps));
            return addReminderRecord(fitnessItem);
        }
        return null;
    }

    private FitnessData addReminderRecord(FitnessItem fitnessItem) {
        fitnessItem.setId(null);
        FitnessData fitnessData = fitnessService.postFitness(this.userContextLite, this.lang, FitnessMapper.INSTANCE.FitnessItemEntityToDto(fitnessItem)).getBody();
        return fitnessData;
    }

    private void makeRedeem(CustomerRedeems customerRedeems) {
        customerRedeemsRepository.save(customerRedeems);
    }
}
