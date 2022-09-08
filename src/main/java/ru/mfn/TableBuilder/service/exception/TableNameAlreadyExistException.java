package ru.mfn.TableBuilder.service.exception;

import ru.mfn.TableBuilder.exception.CustomException;

public class TableNameAlreadyExistException  extends CustomException {
    public TableNameAlreadyExistException(String message) {
        super(message);
    }
}
