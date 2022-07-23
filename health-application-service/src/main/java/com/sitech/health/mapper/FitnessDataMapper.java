package com.sitech.health.mapper;

import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.health.domain.FitnessDataEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FitnessDataMapper {

    FitnessDataMapper INSTANCE = Mappers.getMapper(FitnessDataMapper.class);

//    @Mapping(source = "fitnessData", target = "fromDate", qualifiedBy = LongToTimeStamp.class)
//    @Mapping(source = "toDate", target = "toDate", qualifiedBy = LongToTimeStamp.class)
    FitnessDataEntity dtoToEntity(FitnessData fitnessData);

//    @Mapping(source = "fitnessDataEntity", target = "fromDate", qualifiedBy = TimeStampToLong.class)
//    @Mapping(source = "toDate", target = "toDate", qualifiedBy = TimeStampToLong.class)
    FitnessData entityToDto(FitnessDataEntity fitnessDataEntity);

//    @LongToTimeStamp
//    default LocalDateTime longToTimeStamp(Long value) {
//        System.out.println("<<<<<<<<<<<<<<<<<<");
//        LocalDateTime triggerTime =
//                LocalDateTime.ofInstant(Instant.ofEpochMilli(value), TimeZone.getDefault().toZoneId());
//        return triggerTime;
//    }
//
//    @TimeStampToLong
//    default LocalDateTime timeStampToLong(LocalDateTime value) {
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
//        long val = value.atZone(ZoneId.systemDefault()).toEpochSecond();
//        LocalDateTime ldt = Instant.ofEpochMilli(val).atZone(ZoneId.systemDefault()).toLocalDateTime();
//        return ldt;
//    }

}
