package com.sitech.health.controller;


import com.sitech.dbs.health_service.api.service.v2.CustomerRedeemApi;
import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.health.mapper.FitnessDataMapper;
import com.sitech.health.service.CustomerRedeemService;
import com.sitech.health.service.secuirty.UserContextService;
import com.sitech.health.util.LanguageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class CustomerRedeemController implements CustomerRedeemApi {

    @Autowired
    private CustomerRedeemService clientRedeemService;
    @Autowired
    private UserContextService userContextService;
    @Autowired
    private LanguageUtil languageUtil;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public ResponseEntity<FitnessData> doCustomerRedeem(FitnessData fitnessData) {
        log.info("Start Calling Customer Redeem [ {} ]",fitnessData.toString());
        return ResponseEntity.status(HttpStatus.OK).body(
                FitnessDataMapper.INSTANCE.entityToDto(clientRedeemService.doRedeem(fitnessData))
        );
    }
}
