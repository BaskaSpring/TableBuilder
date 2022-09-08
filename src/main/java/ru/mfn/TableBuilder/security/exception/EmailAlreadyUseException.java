package ru.mfn.TableBuilder.security.exception;


import ru.mfn.TableBuilder.exception.CustomException;

public class EmailAlreadyUseException extends CustomException {
    public EmailAlreadyUseException(String message) {
        super(message);
    }
}
