package com.sitech.health.repository;

import com.sitech.dbs.health_service.api.service.v2.model.HealthData;
import com.sitech.health.domain.FitnessData;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FitnessDataRepository extends PagingAndSortingRepository<FitnessData, String> {

    FitnessData findFirstByCustomerIdOrderByCreatedAtDesc(String customerId);


    FitnessData findFirstByCustomerIdAndDeviceIdOrderByCreatedAtDesc(String customerId , String deviceId);

}
