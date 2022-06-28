package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.health.commons.ServiceConstants;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.FitnessItem;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.mapper.FitnessMapper;
import com.sitech.health.mapper.RedeemMapper;
import com.sitech.health.repository.FitnessRepository;
import com.sitech.health.repository.RedeemRepository;
import com.sitech.health.service.FitnessService;
import com.sitech.health.util.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FitnessServiceImpl implements FitnessService {

    @Autowired
    private FitnessRepository fitnessRepository;
    @Autowired
    private RedeemRepository redeemRepository;
    private String lang;
    @Autowired
    private Translator translator;

    @Override
    public ResponseEntity<Void> deleteFitness(String id) {
        ResponseEntity<FitnessData> fitnessById = getFitnessByCustomerId(id);
        if (!Objects.isNull(fitnessById)) {
            fitnessRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.ERROR_IN_PERSIST_DATA, "jpa.persist.data.error.title", "jpa.persist.data.error.message", this.lang));
        }
    }

    @Override
    public ResponseEntity<FitnessData> getFitnessByCustomerId(String customerId) {

        FitnessItem fitnessItem = fitnessRepository.findFirstByCustomerIdOrderByIdDesc(customerId);
        Map<String, String> additions = new HashMap<>();
        if (!Objects.isNull(fitnessItem)) {
            FitnessData fitnessData = new FitnessData();
            List<com.sitech.dbs.health_service.api.service.v2.model.FitnessItem> lst = new ArrayList<com.sitech.dbs.health_service.api.service.v2.model.FitnessItem>();
            lst.add(FitnessMapper.INSTANCE.FitnessItemEntityToDto(fitnessItem));
            fitnessData.setFitnness(lst);
            RedeemMapper.INSTANCE.redeemEntityToDto(redeemRepository.findByBankId(fitnessItem.getBankId()));
            additions.put(ServiceConstants.USER_NAME_KEY, "Sitech");
            fitnessData.setAdditions(additions);
            return ResponseEntity.status(HttpStatus.OK).body(fitnessData);
        } else {
            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.ERROR_IN_PERSIST_DATA, "jpa.persist.data.error.title", "jpa.persist.data.error.message", this.lang));
        }

    }


    @Override
    public ResponseEntity<FitnessData> postFitness(UserContextDto userContextLite, String requestedLanguage, com.sitech.dbs.health_service.api.service.v2.model.FitnessItem fitnessItem) {
       this.lang = requestedLanguage;
        if (!Objects.isNull(fitnessItem)) {
            FitnessItem fitnessEntity = FitnessMapper.INSTANCE.FitnessItemToEntity(fitnessItem);
            fitnessEntity.setBankId(userContextLite.getBankId());
            fitnessEntity.setCustomerId(userContextLite.getCustomerId());
            FitnessItem savedFitnessItem = fitnessRepository.save(fitnessEntity);
            FitnessData fitnessData = new FitnessData();
            List<com.sitech.dbs.health_service.api.service.v2.model.FitnessItem> lst = new ArrayList<com.sitech.dbs.health_service.api.service.v2.model.FitnessItem>();
            lst.add(FitnessMapper.INSTANCE.FitnessItemEntityToDto(savedFitnessItem));
            fitnessData.setFitnness(lst);
            fitnessData.setRedeem(RedeemMapper.INSTANCE.redeemEntityToDto(redeemRepository.findByBankId(fitnessEntity.getBankId())));
            return ResponseEntity.status(HttpStatus.OK).body(fitnessData);
        } else {
            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.ERROR_IN_PERSIST_DATA, "jpa.persist.data.error.title", "jpa.persist.data.error.message", this.lang));
        }
    }

}
