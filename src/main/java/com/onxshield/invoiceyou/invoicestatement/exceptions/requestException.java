package com.onxshield.invoiceyou.invoicestatement.exceptions;

import org.springframework.http.HttpStatus;

public class requestException extends RuntimeException{

    private HttpStatus httpStatus;
    public requestException(String message) {
        super(message);
    }

    public requestException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}
