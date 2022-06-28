package com.sitech.health.mapper;

import com.sitech.dbs.health_service.api.service.v2.model.FitnessItem;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-28T11:40:47+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
public class FitnessMapperImpl implements FitnessMapper {

    @Override
    public com.sitech.health.domain.FitnessItem FitnessItemToEntity(FitnessItem fitnessItem) {
        if ( fitnessItem == null ) {
            return null;
        }

        com.sitech.health.domain.FitnessItem fitnessItem1 = new com.sitech.health.domain.FitnessItem();

        fitnessItem1.setId( fitnessItem.getId() );
        fitnessItem1.setNumberOfSteps( fitnessItem.getNumberOfSteps() );
        fitnessItem1.setFromDate( fitnessItem.getFromDate() );
        fitnessItem1.setToDate( fitnessItem.getToDate() );
        if ( fitnessItem.getCreatedAt() != null ) {
            fitnessItem1.setCreatedAt( LocalDateTime.parse( fitnessItem.getCreatedAt() ) );
        }
        if ( fitnessItem.getUpdatedAt() != null ) {
            fitnessItem1.setUpdatedAt( LocalDateTime.parse( fitnessItem.getUpdatedAt() ) );
        }

        return fitnessItem1;
    }

    @Override
    public FitnessItem FitnessItemEntityToDto(com.sitech.health.domain.FitnessItem fitnessItem) {
        if ( fitnessItem == null ) {
            return null;
        }

        FitnessItem fitnessItem1 = new FitnessItem();

        fitnessItem1.setId( fitnessItem.getId() );
        fitnessItem1.setNumberOfSteps( fitnessItem.getNumberOfSteps() );
        fitnessItem1.setFromDate( fitnessItem.getFromDate() );
        fitnessItem1.setToDate( fitnessItem.getToDate() );
        if ( fitnessItem.getCreatedAt() != null ) {
            fitnessItem1.setCreatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( fitnessItem.getCreatedAt() ) );
        }
        if ( fitnessItem.getUpdatedAt() != null ) {
            fitnessItem1.setUpdatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( fitnessItem.getUpdatedAt() ) );
        }

        return fitnessItem1;
    }

    @Override
    public List<FitnessItem> FitnessItemEntityToDto(List<com.sitech.health.domain.FitnessItem> lst) {
        if ( lst == null ) {
            return null;
        }

        List<FitnessItem> list = new ArrayList<FitnessItem>( lst.size() );
        for ( com.sitech.health.domain.FitnessItem fitnessItem : lst ) {
            list.add( FitnessItemEntityToDto( fitnessItem ) );
        }

        return list;
    }
}
