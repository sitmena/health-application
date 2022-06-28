package com.sitech.health.mapper;

import com.sitech.health.domain.RedeemItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RedeemMapper {

    RedeemMapper INSTANCE = Mappers.getMapper(RedeemMapper.class);

    RedeemItem redeemItemToEntity(com.sitech.dbs.health_service.api.service.v2.model.RedeemItem redeemItem);

    com.sitech.dbs.health_service.api.service.v2.model.RedeemItem redeemEntityToDto(RedeemItem redeem);

    List<com.sitech.dbs.health_service.api.service.v2.model.RedeemItem> redeemItemEntityToDtos(List<RedeemItem> lst);

}
