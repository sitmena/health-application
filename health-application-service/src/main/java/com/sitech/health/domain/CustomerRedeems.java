package com.sitech.health.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customer_redeems")
@Data
@EntityListeners(AuditingEntityListener.class)
public class CustomerRedeems {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "number_of_point")
    @Size(min = 1, max = 3)
    @NotNull(message = "Number Of Point is mandatory")
    private String numberOfPoints;
    @Column(name = "total_redeemed_steps")
    @NotNull(message = "Total Redeemed Steps is mandatory")
    private String totalRedeemedSteps;
    @Column(name = "bank_id")
    @NotNull(message = "Bank Id is mandatory")
    private String bankId;
    @Column(name = "customer_id")
    @NotNull(message = "Customer Id is mandatory")
    private String customerId;
    @Column(name = "device_id")
    @NotNull(message = "Device Id is mandatory")
    private String deviceId;
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
