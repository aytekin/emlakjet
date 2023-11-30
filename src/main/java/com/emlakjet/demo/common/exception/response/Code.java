package com.emlakjet.demo.common.exception.response;

import lombok.Getter;

@Getter
public class Code {
    private Code() {
        throw new IllegalStateException("Utility class");
    }

    public static final Integer OK = 200;
    public static final Integer SERVER_ERROR = 500;

    /**
     * Business Exception Codes
     */
    public static final Integer BILL_LIMIT_EXCEED = 9000001;


}