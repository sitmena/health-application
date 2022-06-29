package com.sitech.health.service;

import com.sitech.dbs.health_service.api.service.v2.model.DeviceActivation;
import com.sitech.health.commons.UserContextDto;
import org.springframework.http.ResponseEntity;

public interface DeviceActivationService {

    ResponseEntity<DeviceActivation> activateDevice(UserContextDto userContextLite, String requestedLanguage, DeviceActivation deviceActivation);
}
