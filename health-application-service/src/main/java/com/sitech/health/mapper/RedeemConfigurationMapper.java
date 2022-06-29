package com.sitech.health.mapper;

import com.sitech.health.domain.RedeemConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RedeemConfigurationMapper {

    RedeemConfigurationMapper INSTANCE = Mappers.getMapper(RedeemConfigurationMapper.class);

    RedeemConfiguration dtoToEntity(com.sitech.dbs.health_service.api.service.v2.model.RedeemConfiguration redeemConfiguration);
    com.sitech.dbs.health_service.api.service.v2.model.RedeemConfiguration entityToDto(RedeemConfiguration redeem);

}
