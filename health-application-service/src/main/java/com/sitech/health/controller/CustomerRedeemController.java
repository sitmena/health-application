package com.sitech.health.controller;

import com.sitech.dbs.health_service.api.service.v2.ClientRedeemApi;
import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.dbs.health_service.api.service.v2.model.FitnessItem;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.service.CustomerRedeemService;
import com.sitech.health.service.secuirty.UserContextService;
import com.sitech.health.util.LanguageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class CustomerRedeemController implements ClientRedeemApi {

    @Autowired
    private CustomerRedeemService clientRedeemService;
    @Autowired
    private UserContextService userContextService;
    @Autowired
    private LanguageUtil languageUtil;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public ResponseEntity<FitnessData> doRedeem(FitnessItem fitnessItem) {
        UserContextDto userContextLite = userContextService.getUserContextLite();
        return clientRedeemService.doRedeem(userContextLite , languageUtil.getRequestedLanguage(httpServletRequest) ,fitnessItem);

    }
}
