package ru.mfn.TableBuilder.controller.handler;


import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.mfn.TableBuilder.exception.*;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(EmailAlreadyUseException.class)
    public ResponseEntity<ErrorMessage> emailAlreadyException(EmailAlreadyUseException ex) {
        return commonMessage(ex, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UsernameAlreadyUserException.class)
    public ResponseEntity<ErrorMessage> usernameAlreadyException(UsernameAlreadyUserException ex) {
        return commonMessage(ex, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorMessage> roleNotFoundException(RoleNotFoundException ex) {
        return commonMessage(ex, HttpStatus.NOT_ACCEPTABLE);
    }


    private ResponseEntity<ErrorMessage> commonMessage(Exception ex, HttpStatus httpStatus) {
        var message = ex.getMessage();
        log.error(message, ex);
        return new ResponseEntity(message,httpStatus);
    }
}
