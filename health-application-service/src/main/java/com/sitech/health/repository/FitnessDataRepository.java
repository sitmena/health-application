package com.sitech.health.repository;

import com.sitech.health.domain.FitnessData;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FitnessDataRepository extends PagingAndSortingRepository<FitnessData, String> {

    FitnessData findFirstByCustomerIdOrderByIdDesc(String customerId);
}
