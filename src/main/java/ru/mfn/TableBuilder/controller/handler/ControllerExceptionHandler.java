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

    @ExceptionHandler(RefreshTokenNotDatabase.class)
    public ResponseEntity<ErrorMessage> refreshTokenNotDatabase(RefreshTokenNotDatabase ex) {
        return commonMessage(ex, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(RefreshTokenExpired.class)
    public ResponseEntity<ErrorMessage> refreshTokenExpired(RefreshTokenExpired ex) {
        return commonMessage(ex, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(RefreshTokenRandomError.class)
    public ResponseEntity<ErrorMessage> refreshTokenRandomError(RefreshTokenRandomError ex) {
        return commonMessage(ex, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFoundException(UserNotFoundException ex) {
        return commonMessage(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotActiveException.class)
    public ResponseEntity<ErrorMessage> userNotActiveException(UserNotActiveException ex) {
        return commonMessage(ex, HttpStatus.LOCKED);
    }

    @ExceptionHandler(RoleAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> roleAlreadyExistException(RoleAlreadyExistException ex) {
        return commonMessage(ex, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> accessDeniedException(AccessDeniedException ex) {
        return commonMessage(ex, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidNumberIdException.class)
    public ResponseEntity<ErrorMessage> invalidNumberIdException(InvalidNumberIdException ex) {
        return commonMessage(ex, HttpStatus.FORBIDDEN);
    }

    private ResponseEntity<ErrorMessage> commonMessage(Exception ex, HttpStatus httpStatus) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.message = ex.getMessage();
        errorMessage.status = httpStatus.toString();
        log.error(errorMessage.getMessage(),ex);
        return new ResponseEntity(errorMessage,httpStatus);
    }
}
