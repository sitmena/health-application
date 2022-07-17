package com.sitech.health.controller;


import com.sitech.dbs.health_service.api.service.v2.CustomerSynchronizeDataApi;
import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.health.commons.UserContextDto;
import com.sitech.health.exceptions.GenericErrorException;
import com.sitech.health.mapper.FitnessDataMapper;
import com.sitech.health.service.FitnessDataService;
import com.sitech.health.service.secuirty.UserContextService;
import com.sitech.health.util.LanguageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@RestController
public class CustomerSynchronizeDataController implements CustomerSynchronizeDataApi {

    @Autowired
    private LanguageUtil languageUtil;
    @Autowired
    private FitnessDataService fitnessService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private UserContextService userContextService;


    @Override
    public ResponseEntity<FitnessData> synchronizeCustomerData(FitnessData fitnessData) {
        log.info("Start Calling synchronize Customer Data [ {} ]", fitnessData.toString());
        UserContextDto userContextLite = userContextService.getUserContextLite();
        if (!Objects.isNull(fitnessData)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    FitnessDataMapper.INSTANCE.entityToDto(
                            fitnessService.postFitness(
                                    userContextLite,
                                    languageUtil.getRequestedLanguage(httpServletRequest),
                                    FitnessDataMapper.INSTANCE.dtoToEntity(fitnessData)))
            );
        }
        throw new GenericErrorException("Invalid Data");
    }
}
