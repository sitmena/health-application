/*
Boat Generator configuration:
    useBeanValidation: true
    useOptional: false
    addServletRequest: false
    addBindingResult: false
    useLombokAnnotations: false
    openApiNullable: true
    useSetForUniqueItems: false
    useWithModifiers: false
*/
package com.sitech.dbs.health_service.api.service.v2;

import com.sitech.dbs.health_service.api.service.v2.model.Subscription;
import java.util.Set;
import java.util.LinkedHashSet;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "com.backbase.oss.codegen.java.BoatSpringCodeGen", date = "2022-06-29T12:54:44.857345300+03:00[Asia/Amman]")
@Api(value = "Subscription", description = "the Subscription API")
public interface SubscriptionApi {

    /**
     * POST /client-api/v1/clientSubscription
     * Adds or updates a subscribe
     *
     * @param subscription  (optional)
     * @return return general status response (status code 200)
     */
    @ApiOperation(value = "", nickname = "subscribe", notes = "Adds or updates a subscribe", response = Subscription.class, tags={ "subscription", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "return general status response", response = Subscription.class) })
    @RequestMapping(value = "/client-api/v1/clientSubscription",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Subscription> subscribe(
            @ApiParam(value = ""  )
            @Valid
            @RequestBody(required = false) Subscription subscription

    );

}
