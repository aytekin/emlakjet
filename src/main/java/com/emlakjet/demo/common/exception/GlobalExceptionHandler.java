package com.emlakjet.demo.common.exception;

import com.emlakjet.demo.common.exception.response.Code;
import com.emlakjet.demo.common.exception.response.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseBean<Object> exceptionHandler(Exception e) {
        log.error("Server Exception : {}", e);
        return new ResponseBean<>(Code.SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(value = {EmlakjetException.class})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseBean<Object> jetworkHandler(EmlakjetException e) {
        log.error("Business Exception {} {1}", e.getCode(), e);
        return new ResponseBean<>(e.getCode(), e.getMessage());
    }
}