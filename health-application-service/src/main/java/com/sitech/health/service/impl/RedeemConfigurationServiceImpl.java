package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.RedeemConfiguration;
import com.sitech.health.commons.ServiceConstants;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.RedeemConfigurationEntity;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.mapper.RedeemConfigurationMapper;
import com.sitech.health.repository.RedeemConfigurationRepository;
import com.sitech.health.service.RedeemConfigurationService;
import com.sitech.health.util.Translator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public RedeemConfigurationEntity getRedeemConfigurationByBankId(String id) {
        RedeemConfigurationEntity result = redeemRepository.findByBankId(id);
        if (!Objects.isNull(result)) {
            return result;
        } else {
            return new RedeemConfigurationEntity();
        }
    }

    @Override
    public RedeemConfigurationEntity postRedeemConfiguration(UserContextDto userContextLite, String requestedLanguage, RedeemConfiguration redeemConfiguration) {
        this.lang = requestedLanguage;
        if (!Objects.isNull(redeemConfiguration)) {
             RedeemConfigurationEntity redeemEntity = RedeemConfigurationMapper.INSTANCE.dtoToEntity(redeemConfiguration);
             redeemEntity.setBankId(userContextLite.getBankId());
             RedeemConfigurationEntity savedRedeem = redeemRepository.save(redeemEntity);
            return savedRedeem;
        } else {
            throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.ERROR_IN_PERSIST_DATA, "jpa.persist.data.error.title", "jpa.persist.data.error.message", this.lang));
        }
    }
}
