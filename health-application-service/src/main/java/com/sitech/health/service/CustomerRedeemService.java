package com.sitech.health.service;

import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.dbs.health_service.api.service.v2.model.HealthData;
import com.sitech.health.commons.UserContextDto;
import org.springframework.http.ResponseEntity;

public interface CustomerRedeemService {

    ResponseEntity<HealthData> doRedeem(UserContextDto userContextLite, String requestedLanguage, FitnessData fitnessItem);
}
