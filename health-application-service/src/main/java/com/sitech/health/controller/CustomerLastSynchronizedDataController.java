package com.sitech.health.controller;

import com.sitech.dbs.health_service.api.service.v2.CustomerLastSynchronizedDataApi;
import com.sitech.dbs.health_service.api.service.v2.model.HealthData;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.service.CustomerLastSynchronizedDataService;
import com.sitech.health.service.secuirty.UserContextService;
import com.sitech.health.util.LanguageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@RestController
public class CustomerLastSynchronizedDataController implements CustomerLastSynchronizedDataApi {

    @Autowired
    private LanguageUtil languageUtil;
    @Autowired
    private CustomerLastSynchronizedDataService customerLastSynchronizedDataService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private UserContextService userContextService;

    @Override
    public ResponseEntity<HealthData> getCustomerSynchronizedData(UUID deviceId, Boolean activateDevice) {
        boolean activateDeviceVal = Boolean.valueOf(httpServletRequest.getHeader("activateDevice"));
        log.info("Start Calling Customer Synchronized Data [ {}  , {}]", deviceId , activateDeviceVal);
        UserContextDto userContextLite = userContextService.getUserContextLite();
        return ResponseEntity.status(HttpStatus.OK).body(
                customerLastSynchronizedDataService.getCustomerLastSynchronizedData(userContextLite, languageUtil.getRequestedLanguage(httpServletRequest), deviceId, activateDeviceVal)
        );
    }
}
