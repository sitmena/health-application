package com.sitech.health.controller;

import com.sitech.dbs.health_service.api.service.v2.CustomerSubscriptionApi;
import com.sitech.dbs.health_service.api.service.v2.model.DeviceInfo;
import com.sitech.health.domain.SubscriptionEntity;
import com.sitech.health.mapper.CustomerSubscriptionMapper;
import com.sitech.health.service.CustomerSubscriptionService;
import com.sitech.health.service.secuirty.SecurityContextHelper;
import com.sitech.health.util.Translator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CustomerSubscriptionController implements CustomerSubscriptionApi {

    private CustomerSubscriptionService customerSubscriptionService;
    private SecurityContextHelper securityContextHelper;
    private Translator translator;

    public CustomerSubscriptionController(SecurityContextHelper securityContextHelper, Translator translator , CustomerSubscriptionService customerSubscriptionService) {
        this.securityContextHelper = securityContextHelper;
        this.translator = translator;
        this.customerSubscriptionService = customerSubscriptionService;
    }


    @Override
    public ResponseEntity<DeviceInfo> addCustomerSubscription(DeviceInfo deviceInfo) {
        log.info("Start Calling Subscription [ {} ]", deviceInfo.toString());
        SubscriptionEntity entity = customerSubscriptionService.addCustomerSubscription(deviceInfo);
        return ResponseEntity.status(HttpStatus.OK).body(CustomerSubscriptionMapper.INSTANCE.entityToDto(entity));
    }

    @Override
    public ResponseEntity<DeviceInfo> getCustomerSubscription() {
        log.info("Start Calling Get Customer Subscription  .... ");
        List<SubscriptionEntity> subscriptions = customerSubscriptionService.getCustomerSubscriptions();
        if(subscriptions.isEmpty()){
            return ResponseEntity.status(511).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(CustomerSubscriptionMapper.INSTANCE.entityToDto(subscriptions.get(0)));
    }

}
