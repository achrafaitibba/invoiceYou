package com.onxshield.invoiceyou.invoicestatement.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class requestException extends RuntimeException{

    private HttpStatus httpStatus;
    public requestException(String message) {
        super(message);
    }

    public requestException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

}
