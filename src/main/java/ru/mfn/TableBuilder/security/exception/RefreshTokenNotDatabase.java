package ru.mfn.TableBuilder.security.exception;


import ru.mfn.TableBuilder.exception.CustomException;

public class RefreshTokenNotDatabase extends CustomException {
    public RefreshTokenNotDatabase(String message) {
        super(message);
    }
}
