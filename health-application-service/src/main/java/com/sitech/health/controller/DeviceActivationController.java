package com.sitech.health.controller;

import com.sitech.dbs.health_service.api.service.v2.DeviceActivationApi;
import com.sitech.dbs.health_service.api.service.v2.model.DeviceActivation;
import com.sitech.health.service.secuirty.UserContextService;
import com.sitech.health.util.LanguageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class DeviceActivationController implements DeviceActivationApi {

    @Autowired
    private UserContextService userContextService;
    @Autowired
    private LanguageUtil languageUtil;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public ResponseEntity<DeviceActivation> activateDevice(DeviceActivation deviceActivation) {
        return null;
    }
}
