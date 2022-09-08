package ru.mfn.TableBuilder.service.exception;


import ru.mfn.TableBuilder.exception.CustomException;

public class RoleAlreadyExistException extends CustomException {
    public RoleAlreadyExistException(String message) {
        super(message);
    }
}
