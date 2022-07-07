package com.sitech.health.service;


import com.sitech.dbs.health_service.api.service.v2.model.HealthData;
import com.sitech.health.commons.UserContextDto;
import org.springframework.http.ResponseEntity;

public interface SyncedDataService {

    ResponseEntity<HealthData> getCustomerLastSyncData(UserContextDto userContextLite, String requestedLanguage, String deviceId);
}
