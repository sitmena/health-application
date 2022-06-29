package com.sitech.health.mapper;

import com.sitech.dbs.health_service.api.service.v2.model.RedeemConfiguration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-29T12:54:48+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
public class RedeemConfigurationMapperImpl implements RedeemConfigurationMapper {

    @Override
    public com.sitech.health.domain.RedeemConfiguration dtoToEntity(RedeemConfiguration redeemConfiguration) {
        if ( redeemConfiguration == null ) {
            return null;
        }

        com.sitech.health.domain.RedeemConfiguration redeemConfiguration1 = new com.sitech.health.domain.RedeemConfiguration();

        redeemConfiguration1.setId( redeemConfiguration.getId() );
        redeemConfiguration1.setBankName( redeemConfiguration.getBankName() );
        redeemConfiguration1.setCriteriaName( redeemConfiguration.getCriteriaName() );
        redeemConfiguration1.setMeasurement( redeemConfiguration.getMeasurement() );
        redeemConfiguration1.setWeight( redeemConfiguration.getWeight() );
        redeemConfiguration1.setEquivalencePoints( redeemConfiguration.getEquivalencePoints() );
        redeemConfiguration1.setMinimumPointsToBeRedeemed( redeemConfiguration.getMinimumPointsToBeRedeemed() );
        redeemConfiguration1.setMaximumPointsToBeRedeemed( redeemConfiguration.getMaximumPointsToBeRedeemed() );
        if ( redeemConfiguration.getCreatedAt() != null ) {
            redeemConfiguration1.setCreatedAt( LocalDateTime.parse( redeemConfiguration.getCreatedAt() ) );
        }
        if ( redeemConfiguration.getUpdatedAt() != null ) {
            redeemConfiguration1.setUpdatedAt( LocalDateTime.parse( redeemConfiguration.getUpdatedAt() ) );
        }

        return redeemConfiguration1;
    }

    @Override
    public RedeemConfiguration entityToDto(com.sitech.health.domain.RedeemConfiguration redeem) {
        if ( redeem == null ) {
            return null;
        }

        RedeemConfiguration redeemConfiguration = new RedeemConfiguration();

        redeemConfiguration.setId( redeem.getId() );
        redeemConfiguration.setBankName( redeem.getBankName() );
        redeemConfiguration.setCriteriaName( redeem.getCriteriaName() );
        redeemConfiguration.setMeasurement( redeem.getMeasurement() );
        redeemConfiguration.setWeight( redeem.getWeight() );
        redeemConfiguration.setEquivalencePoints( redeem.getEquivalencePoints() );
        redeemConfiguration.setMinimumPointsToBeRedeemed( redeem.getMinimumPointsToBeRedeemed() );
        redeemConfiguration.setMaximumPointsToBeRedeemed( redeem.getMaximumPointsToBeRedeemed() );
        if ( redeem.getCreatedAt() != null ) {
            redeemConfiguration.setCreatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( redeem.getCreatedAt() ) );
        }
        if ( redeem.getUpdatedAt() != null ) {
            redeemConfiguration.setUpdatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( redeem.getUpdatedAt() ) );
        }

        return redeemConfiguration;
    }
}
