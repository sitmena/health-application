package com.sitech.health.controller;

import com.sitech.dbs.health_service.api.service.v2.CustomerDataApi;
import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.dbs.health_service.api.service.v2.model.HealthData;
import com.sitech.health.mapper.FitnessDataMapper;
import com.sitech.health.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class CustomerDataController implements CustomerDataApi {

    @Autowired
    private CustomerDataService customerDataService;

    @Override
    public ResponseEntity<HealthData> getCustomerSynchronizedData(UUID deviceId) {
        return ResponseEntity.status(HttpStatus.OK).body(customerDataService.getCustomerLastActiveSynchronizedData(deviceId));
    }

    @Override
    public ResponseEntity<FitnessData> synchronizeCustomerData(FitnessData fitnessData) {
        return ResponseEntity.status(HttpStatus.OK).body(
                FitnessDataMapper.INSTANCE.entityToDto(customerDataService.addFitnessData(fitnessData)));
    }
}
