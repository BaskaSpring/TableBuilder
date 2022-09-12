package ru.mfn.TableBuilder.service.exception;

import ru.mfn.TableBuilder.exception.CustomException;

public class TableNotFoundException  extends CustomException {
    public TableNotFoundException(String message) {
        super(message);
    }
}
