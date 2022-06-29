package com.sitech.health.service;

import com.sitech.dbs.health_service.api.service.v2.model.Subscription;
import com.sitech.health.commons.UserContextDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubscriptionService {

    ResponseEntity<Subscription> subscribe(UserContextDto userContextLite, String requestedLanguage, Subscription subscription);

    ResponseEntity<List<Subscription>> getCustomerSubscription(UserContextDto userContextLite, String requestedLanguage);
}
