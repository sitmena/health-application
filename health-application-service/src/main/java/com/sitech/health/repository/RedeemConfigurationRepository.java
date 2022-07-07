package com.sitech.health.repository;


import com.sitech.health.domain.RedeemConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RedeemConfigurationRepository extends PagingAndSortingRepository<RedeemConfiguration, String> {

    RedeemConfiguration findByBankId(String bankId);
}
