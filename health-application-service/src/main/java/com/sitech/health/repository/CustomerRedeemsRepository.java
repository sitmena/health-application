package com.sitech.health.repository;

import com.sitech.health.domain.CustomerRedeemsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRedeemsRepository extends PagingAndSortingRepository<CustomerRedeemsEntity, String> {
}
