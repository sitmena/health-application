package com.sitech.health.service.secuirty;


import com.sitech.health.commons.UserContextDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserContextService {

    private static final String BANK_ID_CLAIM = "BankID";
    private static final String CUSTOMER_ID_CLAIM = "CustomerID";
    private static final String USER_TYPE_CLAIM = "UserType";

    private final SecurityContextHelper securityContextHelper;

    @Autowired
    public UserContextService(SecurityContextHelper securityContextHelper) {
        this.securityContextHelper = securityContextHelper;
    }

    public UserContextDto getUserContextLite() {
        String userType = securityContextHelper.getAuthJTWClaim(USER_TYPE_CLAIM);
        String bankId = securityContextHelper.getAuthJTWClaim(BANK_ID_CLAIM);
        String customerId = securityContextHelper.getAuthJTWClaim(CUSTOMER_ID_CLAIM);

        return UserContextDto.builder().userType(userType).bankId(bankId).customerId(customerId).build();
    }
}
