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
 * FitnessData
 */
@javax.annotation.Generated(value = "com.backbase.oss.codegen.java.BoatSpringCodeGen", date = "2022-06-29T12:54:44.857345300+03:00[Asia/Amman]")

public class FitnessData 
 {
    @JsonProperty("id")
    private  UUID id;

    @JsonProperty("numberOfSteps")
    private  String numberOfSteps;

    @JsonProperty("fromDate")
    private  String fromDate;

    @JsonProperty("toDate")
    private  String toDate;

    @JsonProperty("deviceId")
    private  String deviceId;

    @JsonProperty("createdAt")
    private @Pattern(regexp="yyyy-MM-dd HH:mm:ss")  String createdAt;

    @JsonProperty("updatedAt")
    private @Pattern(regexp="yyyy-MM-dd HH:mm:ss")  String updatedAt;

    @JsonProperty("additions")
    private Map<String, String> additions = null;


    public FitnessData id(UUID id) {
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


    public FitnessData numberOfSteps(String numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
        return this;
    }

    /**
     * Get numberOfSteps
     * @return numberOfSteps
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull 
    public  String getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps( String numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }


    public FitnessData fromDate(String fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    /**
     * Get fromDate
     * @return fromDate
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull 
    public  String getFromDate() {
        return fromDate;
    }

    public void setFromDate( String fromDate) {
        this.fromDate = fromDate;
    }


    public FitnessData toDate(String toDate) {
        this.toDate = toDate;
        return this;
    }

    /**
     * Get toDate
     * @return toDate
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull 
    public  String getToDate() {
        return toDate;
    }

    public void setToDate( String toDate) {
        this.toDate = toDate;
    }


    public FitnessData deviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    /**
     * Get deviceId
     * @return deviceId
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull 
    public  String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId( String deviceId) {
        this.deviceId = deviceId;
    }


    public FitnessData createdAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Create date
     * @return createdAt
     */
    @ApiModelProperty(example = "2022-06-27", value = "Create date")
    @Pattern(regexp="yyyy-MM-dd HH:mm:ss") 
    public @Pattern(regexp="yyyy-MM-dd HH:mm:ss")  String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@Pattern(regexp="yyyy-MM-dd HH:mm:ss")  String createdAt) {
        this.createdAt = createdAt;
    }


    public FitnessData updatedAt(String updatedAt) {
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


    public FitnessData additions(Map<String, String> additions) {
        this.additions = additions;
        return this;
    }

    public FitnessData putAdditionsItem(String key, String additionsItem) {
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
        FitnessData fitnessData = (FitnessData) o;
        return Objects.equals(this.id, fitnessData.id) &&
                Objects.equals(this.numberOfSteps, fitnessData.numberOfSteps) &&
                Objects.equals(this.fromDate, fitnessData.fromDate) &&
                Objects.equals(this.toDate, fitnessData.toDate) &&
                Objects.equals(this.deviceId, fitnessData.deviceId) &&
                Objects.equals(this.createdAt, fitnessData.createdAt) &&
                Objects.equals(this.updatedAt, fitnessData.updatedAt) &&
                Objects.equals(this.additions, fitnessData.additions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            numberOfSteps,
            fromDate,
            toDate,
            deviceId,
            createdAt,
            updatedAt,
            additions
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FitnessData {\n");
        
        sb.append("        id: ").append(toIndentedString(id)).append("\n");
        sb.append("        numberOfSteps: ").append(toIndentedString(numberOfSteps)).append("\n");
        sb.append("        fromDate: ").append(toIndentedString(fromDate)).append("\n");
        sb.append("        toDate: ").append(toIndentedString(toDate)).append("\n");
        sb.append("        deviceId: ").append(toIndentedString(deviceId)).append("\n");
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

