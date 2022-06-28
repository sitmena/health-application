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
 * FitnessItem
 */
@javax.annotation.Generated(value = "com.backbase.oss.codegen.java.BoatSpringCodeGen", date = "2022-06-28T11:40:27.758410200+03:00[Asia/Amman]")

public class FitnessItem 
 {
    @JsonProperty("id")
    private  UUID id;

    @JsonProperty("numberOfSteps")
    private  String numberOfSteps;

    @JsonProperty("fromDate")
    private  String fromDate;

    @JsonProperty("toDate")
    private  String toDate;

    @JsonProperty("createdAt")
    private @Pattern(regexp="yyyy-MM-dd HH:mm:ss")  String createdAt;

    @JsonProperty("updatedAt")
    private @Pattern(regexp="yyyy-MM-dd HH:mm:ss")  String updatedAt;

    @JsonProperty("additions")
    private Map<String, String> additions = null;


    public FitnessItem id(UUID id) {
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


    public FitnessItem numberOfSteps(String numberOfSteps) {
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


    public FitnessItem fromDate(String fromDate) {
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


    public FitnessItem toDate(String toDate) {
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


    public FitnessItem createdAt(String createdAt) {
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


    public FitnessItem updatedAt(String updatedAt) {
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


    public FitnessItem additions(Map<String, String> additions) {
        this.additions = additions;
        return this;
    }

    public FitnessItem putAdditionsItem(String key, String additionsItem) {
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
        FitnessItem fitnessItem = (FitnessItem) o;
        return Objects.equals(this.id, fitnessItem.id) &&
                Objects.equals(this.numberOfSteps, fitnessItem.numberOfSteps) &&
                Objects.equals(this.fromDate, fitnessItem.fromDate) &&
                Objects.equals(this.toDate, fitnessItem.toDate) &&
                Objects.equals(this.createdAt, fitnessItem.createdAt) &&
                Objects.equals(this.updatedAt, fitnessItem.updatedAt) &&
                Objects.equals(this.additions, fitnessItem.additions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            numberOfSteps,
            fromDate,
            toDate,
            createdAt,
            updatedAt,
            additions
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FitnessItem {\n");
        
        sb.append("        id: ").append(toIndentedString(id)).append("\n");
        sb.append("        numberOfSteps: ").append(toIndentedString(numberOfSteps)).append("\n");
        sb.append("        fromDate: ").append(toIndentedString(fromDate)).append("\n");
        sb.append("        toDate: ").append(toIndentedString(toDate)).append("\n");
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

