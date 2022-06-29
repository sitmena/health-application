package com.sitech.health.service;

import com.sitech.dbs.health_service.api.service.v2.model.RedeemConfiguration;
import com.sitech.health.commons.UserContextDto;
import org.springframework.http.ResponseEntity;

public interface RedeemConfigurationService {


    RedeemConfiguration getRedeemByBankId(String id);

    ResponseEntity<RedeemConfiguration> postRedeem(UserContextDto userContextLite, String requestedLanguage, RedeemConfiguration redeemConfiguration);
}
