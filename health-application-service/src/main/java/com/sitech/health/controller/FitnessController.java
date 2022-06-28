package com.sitech.health.controller;

import com.sitech.dbs.health_service.api.service.v2.FitnessApi;
import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.dbs.health_service.api.service.v2.model.FitnessItem;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.mapper.FitnessMapper;
import com.sitech.health.service.FitnessService;
import com.sitech.health.service.secuirty.UserContextService;
import com.sitech.health.util.LanguageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class FitnessController implements FitnessApi {

    @Autowired
    private LanguageUtil languageUtil;
    @Autowired
    private FitnessService fitnessService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private UserContextService userContextService;

    @Override
    public ResponseEntity<FitnessData> postFitness(FitnessItem fitnessItem) {
        UserContextDto userContextLite = userContextService.getUserContextLite();
        return fitnessService.postFitness(userContextLite , languageUtil.getRequestedLanguage(httpServletRequest) ,fitnessItem);
    }


}
