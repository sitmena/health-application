package com.sitech.health.service;

import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.health.domain.FitnessDataEntity;

public interface CustomerRedeemService {

    FitnessDataEntity doRedeem(FitnessData fitnessItem);
}
