/*
Boat Generator configuration:
    useBeanValidation: true
    useOptional: false
    addServletRequest: false
    useLombokAnnotations: false
    openApiNullable: true
    useSetForUniqueItems: false
    useWithModifiers: false
*/
package com.sitech.health.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "redeem_configuration")
@Data
@EntityListeners(AuditingEntityListener.class)
public class RedeemConfiguration {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    @Column(name = "bank_id")
    private String bankId;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "criteria_name")
    private String criteriaName;
    @Column(name = "measurement")
    private String measurement;
    @Column(name = "weight")
    private String weight;
    @Column(name = "equivalence_points")
    private String equivalencePoints;
    @Column(name = "minimumPointsToBeRedeemed")
    private Integer minimumPointsToBeRedeemed;
    @Column(name = "maximumPointsToBeRedeemed")
    private Integer maximumPointsToBeRedeemed;
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}

