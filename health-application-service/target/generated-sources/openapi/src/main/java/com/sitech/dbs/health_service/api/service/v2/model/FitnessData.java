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
import com.sitech.dbs.health_service.api.service.v2.model.FitnessItem;
import com.sitech.dbs.health_service.api.service.v2.model.RedeemItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import com.fasterxml.jackson.annotation.*;


/**
 * FitnessData
 */
@javax.annotation.Generated(value = "com.backbase.oss.codegen.java.BoatSpringCodeGen", date = "2022-06-28T11:40:27.758410200+03:00[Asia/Amman]")

public class FitnessData 
 {
    @JsonProperty("fitnness")
    private List<FitnessItem> fitnness = null;

    @JsonProperty("redeem")
    private  RedeemItem redeem;

    @JsonProperty("additions")
    private Map<String, String> additions = null;


    public FitnessData fitnness(List<FitnessItem> fitnness) {
        this.fitnness = fitnness;
        return this;
    }

    public FitnessData addFitnnessItem(FitnessItem fitnnessItem) {
        if (this.fitnness == null) {
            this.fitnness = new ArrayList<>();
        }
        this.fitnness.add(fitnnessItem);
        return this;
    }

    /**
     * Get fitnness
     * @return fitnness
     */
    @ApiModelProperty(value = "")
    @Valid 
    public List<FitnessItem> getFitnness() {
        return fitnness;
    }

    public void setFitnness(List<FitnessItem> fitnness) {
        this.fitnness = fitnness;
    }


    public FitnessData redeem(RedeemItem redeem) {
        this.redeem = redeem;
        return this;
    }

    /**
     * Get redeem
     * @return redeem
     */
    @ApiModelProperty(value = "")
    @Valid 
    public  RedeemItem getRedeem() {
        return redeem;
    }

    public void setRedeem( RedeemItem redeem) {
        this.redeem = redeem;
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
        return Objects.equals(this.fitnness, fitnessData.fitnness) &&
                Objects.equals(this.redeem, fitnessData.redeem) &&
                Objects.equals(this.additions, fitnessData.additions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            fitnness,
            redeem,
            additions
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FitnessData {\n");
        
        sb.append("        fitnness: ").append(toIndentedString(fitnness)).append("\n");
        sb.append("        redeem: ").append(toIndentedString(redeem)).append("\n");
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

