package ru.mfn.TableBuilder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UsernameAlreadyUserException extends  CustomException {
    public UsernameAlreadyUserException(String message) {
        super(message);
    }
}
