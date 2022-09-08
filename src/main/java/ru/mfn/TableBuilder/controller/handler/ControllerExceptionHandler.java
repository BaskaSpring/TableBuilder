package ru.mfn.TableBuilder.controller.handler;


import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.mfn.TableBuilder.payload.exception.InvalidNameException;
import ru.mfn.TableBuilder.payload.exception.InvalidNumberIdException;
import ru.mfn.TableBuilder.payload.exception.LengthStringNotValidException;
import ru.mfn.TableBuilder.security.exception.EmailAlreadyUseException;
import ru.mfn.TableBuilder.security.exception.RefreshTokenExpired;
import ru.mfn.TableBuilder.security.exception.RefreshTokenNotDatabase;
import ru.mfn.TableBuilder.security.exception.RefreshTokenRandomError;
import ru.mfn.TableBuilder.service.exception.*;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(EmailAlreadyUseException.class)
    public ResponseEntity<ErrorMessage> emailAlreadyException(EmailAlreadyUseException ex) {
        return commonMessage(ex, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UsernameAlreadyUseException.class)
    public ResponseEntity<ErrorMessage> usernameAlreadyException(UsernameAlreadyUseException ex) {
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

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<ErrorMessage> invalidNameException(InvalidNameException ex) {
        return commonMessage(ex, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(LengthStringNotValidException.class)
    public ResponseEntity<ErrorMessage> lengthStringNotValidException(LengthStringNotValidException ex) {
        return commonMessage(ex, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(TableNameAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> tableNameAlreadyExistException(TableNameAlreadyExistException ex) {
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
