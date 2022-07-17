package com.sitech.health.mapper;

import com.sitech.dbs.health_service.api.service.v2.model.RedeemConfiguration;
import com.sitech.health.domain.RedeemConfigurationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RedeemConfigurationMapper {

    RedeemConfigurationMapper INSTANCE = Mappers.getMapper(RedeemConfigurationMapper.class);
    RedeemConfigurationEntity dtoToEntity(RedeemConfiguration redeemConfiguration);
    RedeemConfiguration entityToDto(RedeemConfigurationEntity redeem);

}
