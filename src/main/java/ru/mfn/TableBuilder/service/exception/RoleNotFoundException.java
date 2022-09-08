package ru.mfn.TableBuilder.service.exception;


import ru.mfn.TableBuilder.exception.CustomException;

public class RoleNotFoundException extends CustomException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
