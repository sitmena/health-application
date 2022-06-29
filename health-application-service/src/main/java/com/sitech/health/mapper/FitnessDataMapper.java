package com.sitech.health.mapper;

import com.sitech.health.domain.FitnessData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FitnessDataMapper {

    FitnessDataMapper INSTANCE = Mappers.getMapper(FitnessDataMapper.class);

    FitnessData dtoToEntity(com.sitech.dbs.health_service.api.service.v2.model.FitnessData fitnessItem);

    com.sitech.dbs.health_service.api.service.v2.model.FitnessData entityToDto(FitnessData fitnessItem);

}
