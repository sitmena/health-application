package com.sitech.health.service;

import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.FitnessDataEntity;

public interface CustomerRedeemService {

    FitnessDataEntity doRedeem(UserContextDto userContextLite, String requestedLanguage, FitnessData fitnessItem);
}
