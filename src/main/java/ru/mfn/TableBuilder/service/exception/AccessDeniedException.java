package ru.mfn.TableBuilder.service.exception;

import ru.mfn.TableBuilder.exception.CustomException;

public class AccessDeniedException extends CustomException {
    public AccessDeniedException(String message) {
        super(message);
    }
}
