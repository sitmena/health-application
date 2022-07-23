package com.sitech.health.mapper;

import com.sitech.dbs.health_service.api.service.v2.model.DeviceInfo;
import com.sitech.health.domain.SubscriptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface CustomerSubscriptionMapper {

    CustomerSubscriptionMapper INSTANCE = Mappers.getMapper(CustomerSubscriptionMapper.class);

    SubscriptionEntity dtoToEntity(DeviceInfo deviceInfo);

    DeviceInfo entityToDto(SubscriptionEntity subscription);

    List<DeviceInfo> entityToDtoِList(List<SubscriptionEntity> subscriptionList);

    List<SubscriptionEntity> dtoToEntityِList(List<DeviceInfo> subscriptionList);

}
