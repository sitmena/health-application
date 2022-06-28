package com.sitech.health.service.secuirty;

import com.backbase.buildingblocks.jwt.internal.authentication.InternalJwtAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityContextHelper {

    private static final String EMPTY_STRING = "";

    public String getAuthJTWClaim(String claimId) {
        InternalJwtAuthentication authentication = (InternalJwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
        Optional<Object> claim = authentication.getDetails().getClaim(claimId);
        return (String) claim.orElse(EMPTY_STRING);
    }
}
