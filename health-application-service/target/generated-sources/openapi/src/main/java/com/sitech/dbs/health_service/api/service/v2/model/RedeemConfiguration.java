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
package com.sitech.dbs.health_service.api.service.v2.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import com.fasterxml.jackson.annotation.*;


/**
 * RedeemConfiguration
 */
@javax.annotation.Generated(value = "com.backbase.oss.codegen.java.BoatSpringCodeGen", date = "2022-06-29T12:54:44.857345300+03:00[Asia/Amman]")

public class RedeemConfiguration 
 {
    @JsonProperty("id")
    private  UUID id;

    @JsonProperty("bankName")
    private  String bankName;

    @JsonProperty("criteriaName")
    private  String criteriaName;

    @JsonProperty("measurement")
    private  String measurement;

    @JsonProperty("weight")
    private  String weight;

    @JsonProperty("equivalencePoints")
    private  String equivalencePoints;

    @JsonProperty("minimumPointsToBeRedeemed")
    private  Integer minimumPointsToBeRedeemed;

    @JsonProperty("maximumPointsToBeRedeemed")
    private  Integer maximumPointsToBeRedeemed;

    @JsonProperty("createdAt")
    private @Pattern(regexp="yyyy-MM-dd HH:mm:ss")  String createdAt;

    @JsonProperty("updatedAt")
    private @Pattern(regexp="yyyy-MM-dd HH:mm:ss")  String updatedAt;

    @JsonProperty("additions")
    private Map<String, String> additions = null;


    public RedeemConfiguration id(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     * @return id
     */
    @ApiModelProperty(value = "")
    @Valid 
    public  UUID getId() {
        return id;
    }

    public void setId( UUID id) {
        this.id = id;
    }


    public RedeemConfiguration bankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    /**
     * Get bankName
     * @return bankName
     */
    @ApiModelProperty(value = "")
    
    public  String getBankName() {
        return bankName;
    }

    public void setBankName( String bankName) {
        this.bankName = bankName;
    }


    public RedeemConfiguration criteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
        return this;
    }

    /**
     * Get criteriaName
     * @return criteriaName
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull 
    public  String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName( String criteriaName) {
        this.criteriaName = criteriaName;
    }


    public RedeemConfiguration measurement(String measurement) {
        this.measurement = measurement;
        return this;
    }

    /**
     * Get measurement
     * @return measurement
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull 
    public  String getMeasurement() {
        return measurement;
    }

    public void setMeasurement( String measurement) {
        this.measurement = measurement;
    }


    public RedeemConfiguration weight(String weight) {
        this.weight = weight;
        return this;
    }

    /**
     * Get weight
     * @return weight
     */
    @ApiModelProperty(value = "")
    
    public  String getWeight() {
        return weight;
    }

    public void setWeight( String weight) {
        this.weight = weight;
    }


    public RedeemConfiguration equivalencePoints(String equivalencePoints) {
        this.equivalencePoints = equivalencePoints;
        return this;
    }

    /**
     * Get equivalencePoints
     * @return equivalencePoints
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull 
    public  String getEquivalencePoints() {
        return equivalencePoints;
    }

    public void setEquivalencePoints( String equivalencePoints) {
        this.equivalencePoints = equivalencePoints;
    }


    public RedeemConfiguration minimumPointsToBeRedeemed(Integer minimumPointsToBeRedeemed) {
        this.minimumPointsToBeRedeemed = minimumPointsToBeRedeemed;
        return this;
    }

    /**
     * Get minimumPointsToBeRedeemed
     * @return minimumPointsToBeRedeemed
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull 
    public  Integer getMinimumPointsToBeRedeemed() {
        return minimumPointsToBeRedeemed;
    }

    public void setMinimumPointsToBeRedeemed( Integer minimumPointsToBeRedeemed) {
        this.minimumPointsToBeRedeemed = minimumPointsToBeRedeemed;
    }


    public RedeemConfiguration maximumPointsToBeRedeemed(Integer maximumPointsToBeRedeemed) {
        this.maximumPointsToBeRedeemed = maximumPointsToBeRedeemed;
        return this;
    }

    /**
     * Get maximumPointsToBeRedeemed
     * @return maximumPointsToBeRedeemed
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull 
    public  Integer getMaximumPointsToBeRedeemed() {
        return maximumPointsToBeRedeemed;
    }

    public void setMaximumPointsToBeRedeemed( Integer maximumPointsToBeRedeemed) {
        this.maximumPointsToBeRedeemed = maximumPointsToBeRedeemed;
    }


    public RedeemConfiguration createdAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Creation date
     * @return createdAt
     */
    @ApiModelProperty(example = "2022-06-27", value = "Creation date")
    @Pattern(regexp="yyyy-MM-dd HH:mm:ss") 
    public @Pattern(regexp="yyyy-MM-dd HH:mm:ss")  String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@Pattern(regexp="yyyy-MM-dd HH:mm:ss")  String createdAt) {
        this.createdAt = createdAt;
    }


    public RedeemConfiguration updatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    /**
     * Updated date
     * @return updatedAt
     */
    @ApiModelProperty(example = "2022-06-27", value = "Updated date")
    @Pattern(regexp="yyyy-MM-dd HH:mm:ss") 
    public @Pattern(regexp="yyyy-MM-dd HH:mm:ss")  String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(@Pattern(regexp="yyyy-MM-dd HH:mm:ss")  String updatedAt) {
        this.updatedAt = updatedAt;
    }


    public RedeemConfiguration additions(Map<String, String> additions) {
        this.additions = additions;
        return this;
    }

    public RedeemConfiguration putAdditionsItem(String key, String additionsItem) {
        if (this.additions == null) {
            this.additions = new HashMap<>();
        }
        this.additions.put(key, additionsItem);
        return this;
    }

    /**
     * Additional properties
     * @return additions
     */
    @ApiModelProperty(value = "Additional properties")
    
    public Map<String, String> getAdditions() {
        return additions;
    }

    public void setAdditions(Map<String, String> additions) {
        this.additions = additions;
    }




    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RedeemConfiguration redeemConfiguration = (RedeemConfiguration) o;
        return Objects.equals(this.id, redeemConfiguration.id) &&
                Objects.equals(this.bankName, redeemConfiguration.bankName) &&
                Objects.equals(this.criteriaName, redeemConfiguration.criteriaName) &&
                Objects.equals(this.measurement, redeemConfiguration.measurement) &&
                Objects.equals(this.weight, redeemConfiguration.weight) &&
                Objects.equals(this.equivalencePoints, redeemConfiguration.equivalencePoints) &&
                Objects.equals(this.minimumPointsToBeRedeemed, redeemConfiguration.minimumPointsToBeRedeemed) &&
                Objects.equals(this.maximumPointsToBeRedeemed, redeemConfiguration.maximumPointsToBeRedeemed) &&
                Objects.equals(this.createdAt, redeemConfiguration.createdAt) &&
                Objects.equals(this.updatedAt, redeemConfiguration.updatedAt) &&
                Objects.equals(this.additions, redeemConfiguration.additions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            bankName,
            criteriaName,
            measurement,
            weight,
            equivalencePoints,
            minimumPointsToBeRedeemed,
            maximumPointsToBeRedeemed,
            createdAt,
            updatedAt,
            additions
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RedeemConfiguration {\n");
        
        sb.append("        id: ").append(toIndentedString(id)).append("\n");
        sb.append("        bankName: ").append(toIndentedString(bankName)).append("\n");
        sb.append("        criteriaName: ").append(toIndentedString(criteriaName)).append("\n");
        sb.append("        measurement: ").append(toIndentedString(measurement)).append("\n");
        sb.append("        weight: ").append(toIndentedString(weight)).append("\n");
        sb.append("        equivalencePoints: ").append(toIndentedString(equivalencePoints)).append("\n");
        sb.append("        minimumPointsToBeRedeemed: ").append(toIndentedString(minimumPointsToBeRedeemed)).append("\n");
        sb.append("        maximumPointsToBeRedeemed: ").append(toIndentedString(maximumPointsToBeRedeemed)).append("\n");
        sb.append("        createdAt: ").append(toIndentedString(createdAt)).append("\n");
        sb.append("        updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
        sb.append("        additions: ").append(toIndentedString(additions)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n        ");
    }
}
