package com.sitech.health.repository;

import com.sitech.health.domain.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerSubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {

    List<SubscriptionEntity> findByCustomerId(String customerId);
    SubscriptionEntity findByCustomerIdAndDeviceId(String customerId,UUID deviceId);
    @Override
    Optional<SubscriptionEntity> findById(UUID imageId);

}
