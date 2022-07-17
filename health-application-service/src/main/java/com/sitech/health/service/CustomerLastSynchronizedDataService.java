package com.sitech.health.service;


import com.sitech.dbs.health_service.api.service.v2.model.HealthData;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.FitnessDataEntity;

import java.util.UUID;

public interface CustomerLastSynchronizedDataService {

    HealthData getCustomerLastSynchronizedData(UserContextDto userContextLite, String requestedLanguage, UUID deviceId, boolean activateDevice);

    FitnessDataEntity getLastCustomerRecord(UserContextDto userContextLite, String requestedLanguage);
}
