package com.sitech.health.repository;


import com.sitech.health.domain.RedeemConfigurationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RedeemConfigurationRepository extends PagingAndSortingRepository<RedeemConfigurationEntity, String> {

    RedeemConfigurationEntity findByBankId(String bankId);
}
