package ru.mfn.TableBuilder.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UserNotFoundException extends  CustomException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
