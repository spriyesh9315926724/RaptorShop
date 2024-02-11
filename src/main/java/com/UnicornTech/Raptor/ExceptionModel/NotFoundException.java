package com.UnicornTech.Raptor.ExceptionModel;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NotFoundException extends Exception {
    private HttpStatus status;
    private String message;

    public NotFoundException(HttpStatus status,String message){
        super(message);
        this.message = message;
        this.status = status;
    }

}
