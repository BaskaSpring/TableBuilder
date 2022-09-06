package ru.mfn.TableBuilder.exception;



public class RefreshTokenExpired extends CustomException {
    public RefreshTokenExpired(String message) {
        super(message);
    }
}
