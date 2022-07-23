package com.sitech.health.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "fitness_data")
@Data
@EntityListeners(AuditingEntityListener.class)
public class FitnessDataEntity {

    //    @Id
//    @GeneratedValue
//    @Column(name = "id", updatable = false, nullable = false)
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @NotNull(message = "Number Of Steps is mandatory")
    @Column(name = "number_of_steps")
    private Integer numberOfSteps;
    @Column(name = "from_date")
    @NotNull(message = "From Date is mandatory")
    private Long fromDate;
    @Column(name = "to_date")
    @NotNull(message = "To Date is mandatory")
    private Long  toDate;
    @Column(name = "device_id")
    @NotNull(message = "Device Id is mandatory")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID deviceId;
    @Column(name = "bank_id")
    @NotNull(message = "Bank Id is mandatory")
    private String bankId;
    @Column(name = "customer_id")
    @NotNull(message = "Customer Id is mandatory")
    private String customerId;
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
