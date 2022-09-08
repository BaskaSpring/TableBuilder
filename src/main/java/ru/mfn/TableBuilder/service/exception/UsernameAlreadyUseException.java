package ru.mfn.TableBuilder.service.exception;


import ru.mfn.TableBuilder.exception.CustomException;

public class UsernameAlreadyUseException extends CustomException {
    public UsernameAlreadyUseException(String message) {
        super(message);
    }
}
