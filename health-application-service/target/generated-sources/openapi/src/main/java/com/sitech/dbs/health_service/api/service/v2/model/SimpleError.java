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
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import com.fasterxml.jackson.annotation.*;


/**
 * SimpleError
 */
@javax.annotation.Generated(value = "com.backbase.oss.codegen.java.BoatSpringCodeGen", date = "2022-06-29T12:54:44.857345300+03:00[Asia/Amman]")

public class SimpleError 
 {
    @JsonProperty("message")
    private @Size(min=1)  String message;

    @JsonProperty("key")
    private @Size(min=1)  String key;


    public SimpleError message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Any further information
     * @return message
     */
    @ApiModelProperty(value = "Any further information")
    @Size(min=1) 
    public @Size(min=1)  String getMessage() {
        return message;
    }

    public void setMessage(@Size(min=1)  String message) {
        this.message = message;
    }


    public SimpleError key(String key) {
        this.key = key;
        return this;
    }

    /**
     * Error summary
     * @return key
     */
    @ApiModelProperty(value = "Error summary")
    @Size(min=1) 
    public @Size(min=1)  String getKey() {
        return key;
    }

    public void setKey(@Size(min=1)  String key) {
        this.key = key;
    }




    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleError simpleError = (SimpleError) o;
        return Objects.equals(this.message, simpleError.message) &&
                Objects.equals(this.key, simpleError.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            message,
            key
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SimpleError {\n");
        
        sb.append("        message: ").append(toIndentedString(message)).append("\n");
        sb.append("        key: ").append(toIndentedString(key)).append("\n");
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

