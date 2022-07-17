package com.sitech.health.service;

import com.sitech.dbs.health_service.api.service.v2.model.RedeemConfiguration;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.RedeemConfigurationEntity;

public interface RedeemConfigurationService {

    RedeemConfigurationEntity getRedeemConfigurationByBankId(String id);

    RedeemConfigurationEntity postRedeemConfiguration(UserContextDto userContextLite, String requestedLanguage, RedeemConfiguration redeemConfiguration);
}
