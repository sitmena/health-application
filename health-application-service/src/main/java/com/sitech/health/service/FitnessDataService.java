package com.sitech.health.service;


import com.sitech.health.commons.UserContextDto;
import com.sitech.health.domain.FitnessDataEntity;

import java.util.List;
import java.util.UUID;

public interface FitnessDataService {

    List<FitnessDataEntity> getFitnessByCustomerId(String customerId);

    FitnessDataEntity findFirstByCustomerIdAndDeviceIdOrderByCreatedAtDesc(String customerId , UUID deviceId);

    FitnessDataEntity postFitness(UserContextDto userContextLite, String requestedLanguage, FitnessDataEntity fitnessItem);

    FitnessDataEntity findFirstByCustomerIdOrderByCreatedAt(String customerId);

    FitnessDataEntity findById(UUID fitnessId);


}
