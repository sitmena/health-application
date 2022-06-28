package com.sitech.health.service;


import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.dbs.health_service.api.service.v2.model.FitnessItem;
import com.sitech.health.commons.UserContextDto;
import org.springframework.http.ResponseEntity;

public interface FitnessService {

    ResponseEntity<Void> deleteFitness(String id);

    ResponseEntity<FitnessData> getFitnessByCustomerId(String customerId);
//
//    ResponseEntity<FitnessData> postFitness(FitnessItem fitnessItem);

    ResponseEntity<FitnessData> postFitness(UserContextDto userContextLite, String requestedLanguage, FitnessItem fitnessItem);
}
