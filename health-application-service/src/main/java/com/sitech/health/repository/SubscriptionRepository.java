package com.sitech.health.repository;

import com.sitech.health.domain.Subscription;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends PagingAndSortingRepository<Subscription, String> {

    List<Subscription> findByCustomerId(String customerId);

}
