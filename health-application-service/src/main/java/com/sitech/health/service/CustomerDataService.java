package com.sitech.health.service;

import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.dbs.health_service.api.service.v2.model.HealthData;
import com.sitech.health.domain.FitnessDataEntity;

import java.util.UUID;

public interface CustomerDataService {

    HealthData getCustomerLastActiveSynchronizedData(UUID deviceId);

    FitnessDataEntity getLastCustomerRecord();

    FitnessDataEntity addFitnessData( FitnessData fitnessData);
}
