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
import com.fasterxml.jackson.annotation.JsonValue;
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
 * Subscription
 */
@javax.annotation.Generated(value = "com.backbase.oss.codegen.java.BoatSpringCodeGen", date = "2022-06-29T12:54:44.857345300+03:00[Asia/Amman]")

public class Subscription 
 {
    @JsonProperty("id")
    private  UUID id;

    @JsonProperty("deviceId")
    private  String deviceId;

  /**
   * Device Operating System
   */
  public enum DeviceTypeEnum {
    ANDROID("Android"),
    
    IOS("IOS");

    private String value;

    DeviceTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DeviceTypeEnum fromValue(String value) {
      for (DeviceTypeEnum b : DeviceTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

    @JsonProperty("deviceType")
    private  DeviceTypeEnum deviceType;

    @JsonProperty("deviceName")
    private  String deviceName;

  /**
   * Gets or Sets deviceStatus
   */
  public enum DeviceStatusEnum {
    ACTIVE("ACTIVE"),
    
    DEACTIVATE("DEACTIVATE");

    private String value;

    DeviceStatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DeviceStatusEnum fromValue(String value) {
      for (DeviceStatusEnum b : DeviceStatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

    @JsonProperty("deviceStatus")
    private  DeviceStatusEnum deviceStatus;

    @JsonProperty("createdAt")
    private @Pattern(regexp="yyyy-MM-dd HH:mm:ss")  String createdAt;

    @JsonProperty("updatedAt")
    private @Pattern(regexp="yyyy-MM-dd HH:mm:ss")  String updatedAt;

    @JsonProperty("additions")
    private Map<String, String> additions = null;


    public Subscription id(UUID id) {
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


    public Subscription deviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    /**
     * Device Serial number
     * @return deviceId
     */
    @ApiModelProperty(required = true, value = "Device Serial number")
    @NotNull 
    public  String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId( String deviceId) {
        this.deviceId = deviceId;
    }


    public Subscription deviceType(DeviceTypeEnum deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    /**
     * Device Operating System
     * @return deviceType
     */
    @ApiModelProperty(value = "Device Operating System")
    
    public  DeviceTypeEnum getDeviceType() {
        return deviceType;
    }

    public void setDeviceType( DeviceTypeEnum deviceType) {
        this.deviceType = deviceType;
    }


    public Subscription deviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    /**
     * Get deviceName
     * @return deviceName
     */
    @ApiModelProperty(value = "")
    
    public  String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName( String deviceName) {
        this.deviceName = deviceName;
    }


    public Subscription deviceStatus(DeviceStatusEnum deviceStatus) {
        this.deviceStatus = deviceStatus;
        return this;
    }

    /**
     * Get deviceStatus
     * @return deviceStatus
     */
    @ApiModelProperty(value = "")
    
    public  DeviceStatusEnum getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus( DeviceStatusEnum deviceStatus) {
        this.deviceStatus = deviceStatus;
    }


    public Subscription createdAt(String createdAt) {
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


    public Subscription updatedAt(String updatedAt) {
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


    public Subscription additions(Map<String, String> additions) {
        this.additions = additions;
        return this;
    }

    public Subscription putAdditionsItem(String key, String additionsItem) {
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
        Subscription subscription = (Subscription) o;
        return Objects.equals(this.id, subscription.id) &&
                Objects.equals(this.deviceId, subscription.deviceId) &&
                Objects.equals(this.deviceType, subscription.deviceType) &&
                Objects.equals(this.deviceName, subscription.deviceName) &&
                Objects.equals(this.deviceStatus, subscription.deviceStatus) &&
                Objects.equals(this.createdAt, subscription.createdAt) &&
                Objects.equals(this.updatedAt, subscription.updatedAt) &&
                Objects.equals(this.additions, subscription.additions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            deviceId,
            deviceType,
            deviceName,
            deviceStatus,
            createdAt,
            updatedAt,
            additions
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Subscription {\n");
        
        sb.append("        id: ").append(toIndentedString(id)).append("\n");
        sb.append("        deviceId: ").append(toIndentedString(deviceId)).append("\n");
        sb.append("        deviceType: ").append(toIndentedString(deviceType)).append("\n");
        sb.append("        deviceName: ").append(toIndentedString(deviceName)).append("\n");
        sb.append("        deviceStatus: ").append(toIndentedString(deviceStatus)).append("\n");
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

