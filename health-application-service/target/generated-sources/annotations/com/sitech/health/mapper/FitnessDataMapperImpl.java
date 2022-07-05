package com.sitech.health.mapper;

import com.sitech.health.domain.FitnessData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-05T09:25:14+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
public class FitnessDataMapperImpl implements FitnessDataMapper {

    @Override
    public FitnessData dtoToEntity(com.sitech.dbs.health_service.api.service.v2.model.FitnessData fitnessItem) {
        if ( fitnessItem == null ) {
            return null;
        }

        FitnessData fitnessData = new FitnessData();

        fitnessData.setId( fitnessItem.getId() );
        fitnessData.setNumberOfSteps( fitnessItem.getNumberOfSteps() );
        fitnessData.setFromDate( fitnessItem.getFromDate() );
        fitnessData.setToDate( fitnessItem.getToDate() );
        if ( fitnessItem.getCreatedAt() != null ) {
            fitnessData.setCreatedAt( LocalDateTime.parse( fitnessItem.getCreatedAt() ) );
        }
        if ( fitnessItem.getUpdatedAt() != null ) {
            fitnessData.setUpdatedAt( LocalDateTime.parse( fitnessItem.getUpdatedAt() ) );
        }

        return fitnessData;
    }

    @Override
    public com.sitech.dbs.health_service.api.service.v2.model.FitnessData entityToDto(FitnessData fitnessItem) {
        if ( fitnessItem == null ) {
            return null;
        }

        com.sitech.dbs.health_service.api.service.v2.model.FitnessData fitnessData = new com.sitech.dbs.health_service.api.service.v2.model.FitnessData();

        fitnessData.setId( fitnessItem.getId() );
        fitnessData.setNumberOfSteps( fitnessItem.getNumberOfSteps() );
        fitnessData.setFromDate( fitnessItem.getFromDate() );
        fitnessData.setToDate( fitnessItem.getToDate() );
        if ( fitnessItem.getCreatedAt() != null ) {
            fitnessData.setCreatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( fitnessItem.getCreatedAt() ) );
        }
        if ( fitnessItem.getUpdatedAt() != null ) {
            fitnessData.setUpdatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( fitnessItem.getUpdatedAt() ) );
        }

        return fitnessData;
    }
}
