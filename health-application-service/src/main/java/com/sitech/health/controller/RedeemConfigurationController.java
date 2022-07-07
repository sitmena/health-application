package com.sitech.health.controller;

import com.sitech.dbs.health_service.api.service.v2.RedeemConfigurationApi;
import com.sitech.dbs.health_service.api.service.v2.model.RedeemConfiguration;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.service.RedeemConfigurationService;
import com.sitech.health.service.secuirty.UserContextService;
import com.sitech.health.util.LanguageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
public class RedeemConfigurationController implements RedeemConfigurationApi {

    @Autowired
    private LanguageUtil languageUtil;
    @Autowired
    private RedeemConfigurationService redeemService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private UserContextService userContextService;

    @Override
    public ResponseEntity<RedeemConfiguration> postRedeemConfiguration(RedeemConfiguration redeemConfiguration) {
        log.info("Start Calling Redeem Config [ {} ]" , redeemConfiguration.toString());
        UserContextDto userContextLite = userContextService.getUserContextLite();
        return redeemService.postRedeem(userContextLite , languageUtil.getRequestedLanguage(httpServletRequest) , redeemConfiguration);
    }

}
