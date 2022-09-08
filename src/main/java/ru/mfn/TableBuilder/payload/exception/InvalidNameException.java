package ru.mfn.TableBuilder.payload.exception;

import ru.mfn.TableBuilder.exception.CustomException;

public class InvalidNameException  extends CustomException {

    public InvalidNameException(String message) {
        super(message);
    }
}
