package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.RedeemConfiguration;
import com.sitech.health.commons.ServiceConstants;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.mapper.RedeemConfigurationMapper;
import com.sitech.health.repository.RedeemConfigurationRepository;
import com.sitech.health.service.RedeemConfigurationService;
import com.sitech.health.util.Translator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class RedeemConfigurationServiceImpl implements RedeemConfigurationService {

    @Autowired
    private RedeemConfigurationRepository redeemRepository;
    private String lang;
    @Autowired
    private Translator translator;

    @Override
    public RedeemConfiguration getRedeemByBankId(String id) {
        RedeemConfiguration redeemResponse = RedeemConfigurationMapper.INSTANCE.entityToDto(redeemRepository.findByBankId(id));
        return redeemResponse;
    }

    @Override
    public ResponseEntity<RedeemConfiguration> postRedeem(UserContextDto userContextLite, String requestedLanguage, RedeemConfiguration redeemConfiguration) {
        this.lang = requestedLanguage;
        if (!Objects.isNull(redeemConfiguration)) {
             com.sitech.health.domain.RedeemConfiguration redeemEntity = RedeemConfigurationMapper.INSTANCE.dtoToEntity(redeemConfiguration);
             redeemEntity.setBankId(userContextLite.getBankId());
             com.sitech.health.domain.RedeemConfiguration savedRedeem = redeemRepository.save(redeemEntity);
            return ResponseEntity.status(HttpStatus.OK).body(RedeemConfigurationMapper.INSTANCE.entityToDto(savedRedeem));
        } else {
            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.ERROR_IN_PERSIST_DATA, "jpa.persist.data.error.title", "jpa.persist.data.error.message", this.lang));
        }
    }

}
