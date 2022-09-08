package ru.mfn.TableBuilder.payload.exception;

import ru.mfn.TableBuilder.exception.CustomException;

public class LengthStringNotValidException  extends CustomException {
    public LengthStringNotValidException(String message) {
        super(message);
    }
}
