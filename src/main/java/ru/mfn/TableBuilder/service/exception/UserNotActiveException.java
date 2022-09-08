package ru.mfn.TableBuilder.service.exception;


import ru.mfn.TableBuilder.exception.CustomException;

public class UserNotActiveException extends CustomException {
    public UserNotActiveException(String message) {
        super(message);
    }
}
