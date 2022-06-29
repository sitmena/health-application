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
import com.sitech.dbs.health_service.api.service.v2.model.ErrorItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import com.fasterxml.jackson.annotation.*;


/**
 * Error
 */
@javax.annotation.Generated(value = "com.backbase.oss.codegen.java.BoatSpringCodeGen", date = "2022-06-29T12:54:44.857345300+03:00[Asia/Amman]")

public class Error 
 {
    @JsonProperty("message")
    private @Size(min=1)  String message;

    @JsonProperty("key")
    private @Size(min=1)  String key;

    @JsonProperty("errors")
    private List<ErrorItem> errors = null;


    public Error message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Any further information
     * @return message
     */
    @ApiModelProperty(required = true, value = "Any further information")
    @NotNull @Size(min=1) 
    public @Size(min=1)  String getMessage() {
        return message;
    }

    public void setMessage(@Size(min=1)  String message) {
        this.message = message;
    }


    public Error key(String key) {
        this.key = key;
        return this;
    }

    /**
     * Error summary
     * @return key
     */
    @ApiModelProperty(required = true, value = "Error summary")
    @NotNull @Size(min=1) 
    public @Size(min=1)  String getKey() {
        return key;
    }

    public void setKey(@Size(min=1)  String key) {
        this.key = key;
    }


    public Error errors(List<ErrorItem> errors) {
        this.errors = errors;
        return this;
    }

    public Error addErrorsItem(ErrorItem errorsItem) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(errorsItem);
        return this;
    }

    /**
     * Detailed error information
     * @return errors
     */
    @ApiModelProperty(value = "Detailed error information")
    @Valid 
    public List<ErrorItem> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorItem> errors) {
        this.errors = errors;
    }




    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Error error = (Error) o;
        return Objects.equals(this.message, error.message) &&
                Objects.equals(this.key, error.key) &&
                Objects.equals(this.errors, error.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            message,
            key,
            errors
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Error {\n");
        
        sb.append("        message: ").append(toIndentedString(message)).append("\n");
        sb.append("        key: ").append(toIndentedString(key)).append("\n");
        sb.append("        errors: ").append(toIndentedString(errors)).append("\n");
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

