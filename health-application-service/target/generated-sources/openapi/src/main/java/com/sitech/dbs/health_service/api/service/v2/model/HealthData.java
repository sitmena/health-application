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
import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.dbs.health_service.api.service.v2.model.RedeemConfiguration;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import com.fasterxml.jackson.annotation.*;


/**
 * HealthData
 */
@javax.annotation.Generated(value = "com.backbase.oss.codegen.java.BoatSpringCodeGen", date = "2022-06-29T12:54:44.857345300+03:00[Asia/Amman]")

public class HealthData 
 {
    @JsonProperty("fitnnessData")
    private  FitnessData fitnnessData;

    @JsonProperty("redeemConfiguration")
    private  RedeemConfiguration redeemConfiguration;

    @JsonProperty("additions")
    private Map<String, String> additions = null;


    public HealthData fitnnessData(FitnessData fitnnessData) {
        this.fitnnessData = fitnnessData;
        return this;
    }

    /**
     * Get fitnnessData
     * @return fitnnessData
     */
    @ApiModelProperty(value = "")
    @Valid 
    public  FitnessData getFitnnessData() {
        return fitnnessData;
    }

    public void setFitnnessData( FitnessData fitnnessData) {
        this.fitnnessData = fitnnessData;
    }


    public HealthData redeemConfiguration(RedeemConfiguration redeemConfiguration) {
        this.redeemConfiguration = redeemConfiguration;
        return this;
    }

    /**
     * Get redeemConfiguration
     * @return redeemConfiguration
     */
    @ApiModelProperty(value = "")
    @Valid 
    public  RedeemConfiguration getRedeemConfiguration() {
        return redeemConfiguration;
    }

    public void setRedeemConfiguration( RedeemConfiguration redeemConfiguration) {
        this.redeemConfiguration = redeemConfiguration;
    }


    public HealthData additions(Map<String, String> additions) {
        this.additions = additions;
        return this;
    }

    public HealthData putAdditionsItem(String key, String additionsItem) {
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
        HealthData healthData = (HealthData) o;
        return Objects.equals(this.fitnnessData, healthData.fitnnessData) &&
                Objects.equals(this.redeemConfiguration, healthData.redeemConfiguration) &&
                Objects.equals(this.additions, healthData.additions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            fitnnessData,
            redeemConfiguration,
            additions
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class HealthData {\n");
        
        sb.append("        fitnnessData: ").append(toIndentedString(fitnnessData)).append("\n");
        sb.append("        redeemConfiguration: ").append(toIndentedString(redeemConfiguration)).append("\n");
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

