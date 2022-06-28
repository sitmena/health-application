package com.sitech.health.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "fitness")
@Data
@EntityListeners(AuditingEntityListener.class)
public class FitnessItem {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "number_of_steps")
    private String numberOfSteps;
    @Column(name = "fromDate")
    private String fromDate;
    @Column(name = "toDate")
    private String toDate;
    @Column(name = "bank_id")
    @NotNull
    private String bankId;
    @Column(name = "customer_id")
    @NotNull
    private String customerId;
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
