package com.sitech.health.commons;

public class ServiceConstants {

    public static final String ENGLISH_LANGUAGE = "en";
    public static final String ARABIC_LANGUAGE = "ar";
    public static final String USER_NAME_KEY = "USER_NAME";
    public static final String STEPS_CRITERIA_NAME = "STEPS";
    public static final String AUTHORIZATION_HEDER = "Authorization";
    public static final String ERROR_IN_PERSIST_DATA = "500";
    public static final String ERROR_REDEEM_CONVERTER = "500";
    public static final String OPERATION_NOT_FOUND_ERROR = "500";
    public static final String NOT_VALID_DEVICE_ERROR = "500";
    public static final String DEVICE_NOT_ACTIVATED_ERROR = "511";
    public static final String USER_ALREADY_SUBSCRIBED_ERROR = "510";
    public static final String BANK_ID_CLAIM = "BankID";
    public static final String SUB_CLAIM = "sub";
    public static final String CUSTOMER_ID_CLAIM = "CustomerID";

    private ServiceConstants() {
        throw new IllegalStateException("Constants class");
    }

}
