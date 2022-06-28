package com.sitech.health.repository;

import com.sitech.health.domain.FitnessItem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FitnessRepository extends PagingAndSortingRepository<FitnessItem, String> {
    @Override
    List<FitnessItem> findAll();

    List<FitnessItem> findByCustomerId(String customerId);

    FitnessItem findFirstByCustomerIdOrderByIdDesc(String customerId);


}
