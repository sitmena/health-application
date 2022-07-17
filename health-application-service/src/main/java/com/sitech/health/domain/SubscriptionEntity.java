package com.sitech.health.domain;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "subscription")
@Data
@EntityListeners(AuditingEntityListener.class)
public class SubscriptionEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(name = "bank_id")
    private String bankId;
    @Column(name = "deviceId")
    private  UUID deviceId;
    @Column(name = "customerId")
    private  String customerId;
    @Column(name = "deviceType")
    @Enumerated(EnumType.ORDINAL)
    private DeviceType deviceType;
    @Column(name = "deviceName")
    private  String deviceName;
    @Column(name = "deviceStatus")
    @Enumerated(EnumType.ORDINAL)
    private DeviceStatus deviceStatus;
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
