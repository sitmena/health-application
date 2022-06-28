package com.sitech.health.exceptions;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ServiceExceptionHandler {

    @ExceptionHandler({GenericErrorException.class})
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleGenericException(GenericErrorException e) {
        String[] res = e.getMessage().trim().split("\\s*~\\s*");
        ErrorResponse message = new ErrorResponse(res[0], res[1], res[2]);
        return new ResponseEntity<ErrorResponse>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
