package ru.mfn.TableBuilder.payload.exception;

import ru.mfn.TableBuilder.exception.CustomException;

public class InvalidNumberIdException  extends CustomException {
    public InvalidNumberIdException(String message) {
        super(message);
    }
}
