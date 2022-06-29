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

import com.sitech.dbs.health_service.api.service.v2.model.Error;
import com.sitech.dbs.health_service.api.service.v2.model.FitnessData;
import com.sitech.dbs.health_service.api.service.v2.model.HealthData;
import com.sitech.dbs.health_service.api.service.v2.model.SimpleError;
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
@Api(value = "FitnessData", description = "the FitnessData API")
public interface FitnessDataApi {

    /**
     * POST /client-api/v1/fitnessData
     * Adds or updates a redeem
     *
     * @param fitnessData  (optional)
     * @return return general status response (status code 200)
     *         or If one or more request parameters don&#39;t comply with the specification (status code 400)
     *         or If a runtime error occurs while processing the request (status code 500)
     */
    @ApiOperation(value = "", nickname = "postFitness", notes = "Adds or updates a redeem", response = HealthData.class, tags={ "fitnessData", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "return general status response", response = HealthData.class),
        @ApiResponse(code = 400, message = "If one or more request parameters don't comply with the specification", response = Error.class),
        @ApiResponse(code = 500, message = "If a runtime error occurs while processing the request", response = SimpleError.class) })
    @RequestMapping(value = "/client-api/v1/fitnessData",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<HealthData> postFitness(
            @ApiParam(value = ""  )
            @Valid
            @RequestBody(required = false) FitnessData fitnessData

    );

}