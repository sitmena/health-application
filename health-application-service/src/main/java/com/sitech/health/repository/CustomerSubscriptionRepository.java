package com.sitech.health.repository;

import com.sitech.health.domain.DeviceStatus;
import com.sitech.health.domain.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerSubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {

//    List<SubscriptionEntity> findByCustomerIdOrderByDeviceStatusACTIVE(String customerId);
    SubscriptionEntity findByCustomerIdAndDeviceId(String customerId,UUID deviceId);
    @Override
    Optional<SubscriptionEntity> findById(UUID imageId);

//    @Query("SELECT s FROM SubscriptionEntity s WHERE ?1 ORDER BY  ")
////    select * from subscription where customer_id = 2058971 AND device_id =   ORDER BY device_status DESC;
//    List<SubscriptionEntity> findActiveCustomer(String customerId);


    // select * from subscription where customer_id = 2058971 AND device_status = 1 order by created_at limit 1;

    List<SubscriptionEntity> findFirstByCustomerIdAndDeviceStatusOrderByCreatedAt(String customerId, DeviceStatus deviceStatus);

    List<SubscriptionEntity> findByCustomerIdOrderByDeviceStatus(String customerId);
}
