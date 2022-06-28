package com.sitech.health.service;

import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
//import com.sitech.health.domain.FitnessItem;
import com.sitech.dbs.health_service.api.service.v2.model.FitnessItem;
import com.sitech.health.commons.UserContextDto;
import org.springframework.http.ResponseEntity;

public interface CustomerRedeemService {

    ResponseEntity<FitnessData> doRedeem(UserContextDto userContextLite, String requestedLanguage, FitnessItem fitnessItem);
}
