package ru.mfn.TableBuilder.security.exception;


import ru.mfn.TableBuilder.exception.CustomException;

public class RefreshTokenExpired extends CustomException {
    public RefreshTokenExpired(String message) {
        super(message);
    }
}
