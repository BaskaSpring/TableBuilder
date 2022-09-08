package ru.mfn.TableBuilder.service.exception;


import ru.mfn.TableBuilder.exception.CustomException;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
