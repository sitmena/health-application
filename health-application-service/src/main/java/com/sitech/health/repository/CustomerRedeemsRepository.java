package com.sitech.health.repository;

import com.sitech.health.domain.CustomerRedeems;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRedeemsRepository extends PagingAndSortingRepository<CustomerRedeems, String> {
}
