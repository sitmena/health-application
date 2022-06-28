package com.sitech.health.service;

import com.sitech.dbs.health_service.api.service.v2.model.RedeemItem;
import com.sitech.health.commons.UserContextDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface RedeemService {

    ResponseEntity<Void> deleteRedeem(String id);

    ResponseEntity<RedeemItem> getRedeemById(UUID id);

    com.sitech.health.domain.RedeemItem getRedeemByBankId(String id);

    ResponseEntity<RedeemItem> postRedeem(UserContextDto userContextLite, String requestedLanguage, RedeemItem redeemItem);
}
