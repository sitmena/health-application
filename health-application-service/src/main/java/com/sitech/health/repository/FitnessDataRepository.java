package com.sitech.health.repository;

import com.sitech.health.domain.FitnessDataEntity;
import com.sitech.health.domain.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FitnessDataRepository extends JpaRepository<FitnessDataEntity, UUID> {

    FitnessDataEntity findFirstByCustomerIdAndDeviceIdOrderByCreatedAtDesc(String customerId, UUID deviceId);

    List<FitnessDataEntity> findByCustomerId(String customerId);

    FitnessDataEntity findFirstByCustomerIdOrderByCreatedAt(String customerId);

    @Override
    Optional<FitnessDataEntity> findById(UUID imageId);
}
