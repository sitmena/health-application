package com.sitech.health.mapper;

import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.health.domain.FitnessDataEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FitnessDataMapper {

    FitnessDataMapper INSTANCE = Mappers.getMapper(FitnessDataMapper.class);

    FitnessDataEntity dtoToEntity(FitnessData fitnessData);

    FitnessData entityToDto(FitnessDataEntity fitnessDataEntity);

}
