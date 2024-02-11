package com.UnicornTech.Raptor.ExceptionHandler;
import com.UnicornTech.Raptor.ExceptionModel.ExceptionModal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ExceptionModal.class)
    public ResponseEntity<ExceptionModal> notfoundExceptionHandler(ExceptionModal exception){
        return new ResponseEntity<ExceptionModal>(
                new ExceptionModal(HttpStatus.NOT_FOUND , exception.getMessage() ),
                HttpStatus.NOT_FOUND
        );
    }

}
