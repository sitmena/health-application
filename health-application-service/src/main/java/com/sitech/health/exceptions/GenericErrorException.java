package com.sitech.health.exceptions;

import com.backbase.buildingblocks.presentation.errors.ApiErrorException;

/**
 * @author Mohammd Al-Ajweh
 * @date 11/20/2021 9:09 PM
 */

public class GenericErrorException extends ApiErrorException {

    public GenericErrorException(String msg) {
        super(msg);
    }
}
