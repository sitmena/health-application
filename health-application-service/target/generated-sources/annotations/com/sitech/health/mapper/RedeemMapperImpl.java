package com.sitech.health.mapper;

import com.sitech.health.domain.RedeemItem;
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
public class RedeemMapperImpl implements RedeemMapper {

    @Override
    public RedeemItem redeemItemToEntity(com.sitech.dbs.health_service.api.service.v2.model.RedeemItem redeemItem) {
        if ( redeemItem == null ) {
            return null;
        }

        RedeemItem redeemItem1 = new RedeemItem();

        redeemItem1.setId( redeemItem.getId() );
        redeemItem1.setBankName( redeemItem.getBankName() );
        redeemItem1.setCriteriaName( redeemItem.getCriteriaName() );
        redeemItem1.setMeasurement( redeemItem.getMeasurement() );
        redeemItem1.setWeight( redeemItem.getWeight() );
        redeemItem1.setEquivalencePoints( redeemItem.getEquivalencePoints() );
        if ( redeemItem.getCreatedAt() != null ) {
            redeemItem1.setCreatedAt( LocalDateTime.parse( redeemItem.getCreatedAt() ) );
        }
        if ( redeemItem.getUpdatedAt() != null ) {
            redeemItem1.setUpdatedAt( LocalDateTime.parse( redeemItem.getUpdatedAt() ) );
        }

        return redeemItem1;
    }

    @Override
    public com.sitech.dbs.health_service.api.service.v2.model.RedeemItem redeemEntityToDto(RedeemItem redeem) {
        if ( redeem == null ) {
            return null;
        }

        com.sitech.dbs.health_service.api.service.v2.model.RedeemItem redeemItem = new com.sitech.dbs.health_service.api.service.v2.model.RedeemItem();

        redeemItem.setId( redeem.getId() );
        redeemItem.setBankName( redeem.getBankName() );
        redeemItem.setCriteriaName( redeem.getCriteriaName() );
        redeemItem.setMeasurement( redeem.getMeasurement() );
        redeemItem.setWeight( redeem.getWeight() );
        redeemItem.setEquivalencePoints( redeem.getEquivalencePoints() );
        if ( redeem.getCreatedAt() != null ) {
            redeemItem.setCreatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( redeem.getCreatedAt() ) );
        }
        if ( redeem.getUpdatedAt() != null ) {
            redeemItem.setUpdatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( redeem.getUpdatedAt() ) );
        }

        return redeemItem;
    }

    @Override
    public List<com.sitech.dbs.health_service.api.service.v2.model.RedeemItem> redeemItemEntityToDtos(List<RedeemItem> lst) {
        if ( lst == null ) {
            return null;
        }

        List<com.sitech.dbs.health_service.api.service.v2.model.RedeemItem> list = new ArrayList<com.sitech.dbs.health_service.api.service.v2.model.RedeemItem>( lst.size() );
        for ( RedeemItem redeemItem : lst ) {
            list.add( redeemEntityToDto( redeemItem ) );
        }

        return list;
    }
}
