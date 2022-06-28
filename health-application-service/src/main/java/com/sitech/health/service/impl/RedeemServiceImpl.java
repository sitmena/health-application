package com.sitech.health.service.impl;


import com.sitech.health.commons.ServiceConstants;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.RedeemItem;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.mapper.RedeemMapper;
import com.sitech.health.repository.RedeemRepository;
import com.sitech.health.service.RedeemService;
import com.sitech.health.util.Translator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class RedeemServiceImpl implements RedeemService {

    @Autowired
    private RedeemRepository redeemRepository;
    private String lang;
    @Autowired
    private Translator translator;


    @Override
    public ResponseEntity<Void> deleteRedeem(String id) {
        com.sitech.dbs.health_service.api.service.v2.model.RedeemItem redeemItem = getRedeemById(UUID. fromString(id)).getBody();
        if(!Objects.isNull(redeemItem)){
            redeemRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }else{
            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.ERROR_IN_PERSIST_DATA, "jpa.persist.data.error.title", "jpa.persist.data.error.message", this.lang));
        }
    }

    @Override
    public ResponseEntity<com.sitech.dbs.health_service.api.service.v2.model.RedeemItem> getRedeemById(UUID id) {
        RedeemItem redeemResponse = redeemRepository.findById(id);
        if(!Objects.isNull(redeemResponse)) {
            return ResponseEntity.status(HttpStatus.OK).body(RedeemMapper.INSTANCE.redeemEntityToDto(redeemResponse));
        }else{
            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.ERROR_IN_PERSIST_DATA, "jpa.persist.data.error.title", "jpa.persist.data.error.message", this.lang));
        }
    }

    @Override
    public RedeemItem getRedeemByBankId(String id) {
        RedeemItem redeemResponse = redeemRepository.findByBankId(id);
        return redeemResponse;
    }

    @Override
    public ResponseEntity<com.sitech.dbs.health_service.api.service.v2.model.RedeemItem> postRedeem(UserContextDto userContextLite, String requestedLanguage, com.sitech.dbs.health_service.api.service.v2.model.RedeemItem redeemItem) {
       this.lang = requestedLanguage;
        if (!Objects.isNull(redeemItem)) {
             RedeemItem redeemEntity = RedeemMapper.INSTANCE.redeemItemToEntity(redeemItem);
             redeemEntity.setBankId(userContextLite.getBankId());
             RedeemItem savedRedeem = redeemRepository.save(redeemEntity);
            return ResponseEntity.status(HttpStatus.OK).body(RedeemMapper.INSTANCE.redeemEntityToDto(savedRedeem));
        } else {
            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.ERROR_IN_PERSIST_DATA, "jpa.persist.data.error.title", "jpa.persist.data.error.message", this.lang));
        }
    }

}
