package com.UnicornTech.Raptor.ExceptionHandler;
import com.UnicornTech.Raptor.ExceptionModel.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundException> notfoundExceptionHandler(NotFoundException exception){
        return new ResponseEntity<NotFoundException>(
                new NotFoundException(HttpStatus.NOT_FOUND , exception.getMessage() ),
                HttpStatus.NOT_FOUND
        );
    }

}
