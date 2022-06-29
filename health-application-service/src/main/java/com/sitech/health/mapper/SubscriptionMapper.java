package com.sitech.health.mapper;

import com.sitech.health.domain.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubscriptionMapper {

    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    Subscription dtoToEntity(com.sitech.dbs.health_service.api.service.v2.model.Subscription subscription);

    com.sitech.dbs.health_service.api.service.v2.model.Subscription entityToDto(Subscription subscription);

    List<com.sitech.dbs.health_service.api.service.v2.model.Subscription> entityToDtoِList(List<Subscription> subscriptionList);

    List<Subscription> dtoToEntityِList(List<com.sitech.dbs.health_service.api.service.v2.model.Subscription> subscriptionList);

}
