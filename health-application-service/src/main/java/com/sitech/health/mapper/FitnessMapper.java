package com.sitech.health.mapper;

import com.sitech.dbs.health_service.api.service.v2.model.FitnessItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FitnessMapper {

    FitnessMapper INSTANCE = Mappers.getMapper(FitnessMapper.class);

    com.sitech.health.domain.FitnessItem FitnessItemToEntity(FitnessItem fitnessItem);

    FitnessItem FitnessItemEntityToDto(com.sitech.health.domain.FitnessItem fitnessItem);

    List<FitnessItem> FitnessItemEntityToDto(List<com.sitech.health.domain.FitnessItem> lst);

}
