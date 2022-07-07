package com.sitech.health.controller;

import com.sitech.dbs.health_service.api.service.v2.SyncedDataApi;
import com.sitech.dbs.health_service.api.service.v2.model.HealthData;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.service.FitnessDataService;
import com.sitech.health.service.SyncedDataService;
import com.sitech.health.service.secuirty.UserContextService;
import com.sitech.health.util.LanguageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class SyncedDataController implements SyncedDataApi {

    @Autowired
    private LanguageUtil languageUtil;
    @Autowired
    private SyncedDataService syncedDataService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private UserContextService userContextService;


    @Override
    public ResponseEntity<HealthData> getCustomerLastSyncData(String deviceId) {

        UserContextDto userContextLite = userContextService.getUserContextLite();
        return syncedDataService.getCustomerLastSyncData(userContextLite,languageUtil.getRequestedLanguage(httpServletRequest) ,deviceId);
    }
}
