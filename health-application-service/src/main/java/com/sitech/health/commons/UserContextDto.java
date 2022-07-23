package com.sitech.health.commons;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserContextDto {
    private String userId;
    private String bankId;
    private String userType;
    private String customerId;
    private String requestedLanguage;
}
