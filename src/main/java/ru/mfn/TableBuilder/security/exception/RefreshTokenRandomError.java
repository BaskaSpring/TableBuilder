package ru.mfn.TableBuilder.security.exception;


import ru.mfn.TableBuilder.exception.CustomException;

public class RefreshTokenRandomError extends CustomException {
    public RefreshTokenRandomError(String message) {
        super(message);
    }
}
