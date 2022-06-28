package com.sitech.health.repository;


import com.sitech.health.domain.RedeemItem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RedeemRepository extends PagingAndSortingRepository<RedeemItem, String> {

    @Override
    List<RedeemItem> findAll();
    
    RedeemItem findByBankId(String bankId);

    RedeemItem findById(UUID id);
}
