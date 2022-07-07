package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.HealthData;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.service.FitnessDataService;
import com.sitech.health.service.SyncedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class SyncedDataServiceImpl implements SyncedDataService {


    @Autowired
    private FitnessDataService fitnessDataService;


    @Override
    public ResponseEntity<HealthData> getCustomerLastSyncData(UserContextDto userContextLite, String requestedLanguage, String deviceId) {
        return fitnessDataService.findFirstByCustomerIdAndDeviceIdOrderByCreatedAtDesc(userContextLite.getCustomerId() , deviceId);
    }
}
