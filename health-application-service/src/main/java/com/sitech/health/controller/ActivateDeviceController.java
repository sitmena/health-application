package com.sitech.health.controller;

import com.sitech.dbs.health_service.api.service.v2.ActivateDeviceApi;
import com.sitech.dbs.health_service.api.service.v2.model.DeviceInfo;
import com.sitech.health.domain.SubscriptionEntity;
import com.sitech.health.service.CustomerSubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ActivateDeviceController implements ActivateDeviceApi {

    @Autowired
    private CustomerSubscriptionService customerSubscriptionService;

    @Override
    public ResponseEntity<Void> activateCustomerDevice(DeviceInfo deviceInfo) {
        log.info("Start Calling Activate Customer Device [ {} ]", deviceInfo.toString());
        SubscriptionEntity sub = customerSubscriptionService.activateDevice(deviceInfo);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
