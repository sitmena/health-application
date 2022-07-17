package com.sitech.health.mapper;

import com.sitech.health.domain.SubscriptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface CustomerSubscriptionMapper {

    CustomerSubscriptionMapper INSTANCE = Mappers.getMapper(CustomerSubscriptionMapper.class);

    SubscriptionEntity dtoToEntity(com.sitech.dbs.health_service.api.service.v2.model.Subscription subscription);

    com.sitech.dbs.health_service.api.service.v2.model.Subscription entityToDto(SubscriptionEntity subscription);

    List<com.sitech.dbs.health_service.api.service.v2.model.Subscription> entityToDtoِList(List<SubscriptionEntity> subscriptionList);

    List<SubscriptionEntity> dtoToEntityِList(List<com.sitech.dbs.health_service.api.service.v2.model.Subscription> subscriptionList);

}
