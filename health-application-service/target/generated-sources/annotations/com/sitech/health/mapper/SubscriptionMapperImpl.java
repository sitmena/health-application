package com.sitech.health.mapper;

import com.sitech.dbs.health_service.api.service.v2.model.Subscription;
import com.sitech.dbs.health_service.api.service.v2.model.Subscription.DeviceStatusEnum;
import com.sitech.dbs.health_service.api.service.v2.model.Subscription.DeviceTypeEnum;
import com.sitech.health.domain.DeviceStatus;
import com.sitech.health.domain.DeviceType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-05T09:25:14+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
public class SubscriptionMapperImpl implements SubscriptionMapper {

    @Override
    public com.sitech.health.domain.Subscription dtoToEntity(Subscription subscription) {
        if ( subscription == null ) {
            return null;
        }

        com.sitech.health.domain.Subscription subscription1 = new com.sitech.health.domain.Subscription();

        subscription1.setId( subscription.getId() );
        subscription1.setDeviceId( subscription.getDeviceId() );
        subscription1.setDeviceType( deviceTypeEnumToDeviceType( subscription.getDeviceType() ) );
        subscription1.setDeviceName( subscription.getDeviceName() );
        subscription1.setDeviceStatus( deviceStatusEnumToDeviceStatus( subscription.getDeviceStatus() ) );
        if ( subscription.getCreatedAt() != null ) {
            subscription1.setCreatedAt( LocalDateTime.parse( subscription.getCreatedAt() ) );
        }
        if ( subscription.getUpdatedAt() != null ) {
            subscription1.setUpdatedAt( LocalDateTime.parse( subscription.getUpdatedAt() ) );
        }

        return subscription1;
    }

    @Override
    public Subscription entityToDto(com.sitech.health.domain.Subscription subscription) {
        if ( subscription == null ) {
            return null;
        }

        Subscription subscription1 = new Subscription();

        subscription1.setId( subscription.getId() );
        subscription1.setDeviceId( subscription.getDeviceId() );
        subscription1.setDeviceType( deviceTypeToDeviceTypeEnum( subscription.getDeviceType() ) );
        subscription1.setDeviceName( subscription.getDeviceName() );
        subscription1.setDeviceStatus( deviceStatusToDeviceStatusEnum( subscription.getDeviceStatus() ) );
        if ( subscription.getCreatedAt() != null ) {
            subscription1.setCreatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( subscription.getCreatedAt() ) );
        }
        if ( subscription.getUpdatedAt() != null ) {
            subscription1.setUpdatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( subscription.getUpdatedAt() ) );
        }

        return subscription1;
    }

    @Override
    public List<Subscription> entityToDtoِList(List<com.sitech.health.domain.Subscription> subscriptionList) {
        if ( subscriptionList == null ) {
            return null;
        }

        List<Subscription> list = new ArrayList<Subscription>( subscriptionList.size() );
        for ( com.sitech.health.domain.Subscription subscription : subscriptionList ) {
            list.add( entityToDto( subscription ) );
        }

        return list;
    }

    @Override
    public List<com.sitech.health.domain.Subscription> dtoToEntityِList(List<Subscription> subscriptionList) {
        if ( subscriptionList == null ) {
            return null;
        }

        List<com.sitech.health.domain.Subscription> list = new ArrayList<com.sitech.health.domain.Subscription>( subscriptionList.size() );
        for ( Subscription subscription : subscriptionList ) {
            list.add( dtoToEntity( subscription ) );
        }

        return list;
    }

    protected DeviceType deviceTypeEnumToDeviceType(DeviceTypeEnum deviceTypeEnum) {
        if ( deviceTypeEnum == null ) {
            return null;
        }

        DeviceType deviceType;

        switch ( deviceTypeEnum ) {
            case ANDROID: deviceType = DeviceType.ANDROID;
            break;
            case IOS: deviceType = DeviceType.IOS;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + deviceTypeEnum );
        }

        return deviceType;
    }

    protected DeviceStatus deviceStatusEnumToDeviceStatus(DeviceStatusEnum deviceStatusEnum) {
        if ( deviceStatusEnum == null ) {
            return null;
        }

        DeviceStatus deviceStatus;

        switch ( deviceStatusEnum ) {
            case ACTIVE: deviceStatus = DeviceStatus.ACTIVE;
            break;
            case DEACTIVATE: deviceStatus = DeviceStatus.DEACTIVATE;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + deviceStatusEnum );
        }

        return deviceStatus;
    }

    protected DeviceTypeEnum deviceTypeToDeviceTypeEnum(DeviceType deviceType) {
        if ( deviceType == null ) {
            return null;
        }

        DeviceTypeEnum deviceTypeEnum;

        switch ( deviceType ) {
            case ANDROID: deviceTypeEnum = DeviceTypeEnum.ANDROID;
            break;
            case IOS: deviceTypeEnum = DeviceTypeEnum.IOS;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + deviceType );
        }

        return deviceTypeEnum;
    }

    protected DeviceStatusEnum deviceStatusToDeviceStatusEnum(DeviceStatus deviceStatus) {
        if ( deviceStatus == null ) {
            return null;
        }

        DeviceStatusEnum deviceStatusEnum;

        switch ( deviceStatus ) {
            case ACTIVE: deviceStatusEnum = DeviceStatusEnum.ACTIVE;
            break;
            case DEACTIVATE: deviceStatusEnum = DeviceStatusEnum.DEACTIVATE;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + deviceStatus );
        }

        return deviceStatusEnum;
    }
}
