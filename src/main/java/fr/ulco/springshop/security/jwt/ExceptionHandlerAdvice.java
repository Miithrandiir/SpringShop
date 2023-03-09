package fr.ulco.springshop.security.jwt;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleAllExceptions(Throwable t) {

        return new ResponseEntity<Object>(
                "Internal Server Error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
