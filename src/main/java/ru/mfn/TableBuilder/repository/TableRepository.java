package ru.mfn.TableBuilder.repository;

import ru.mfn.TableBuilder.payload.exception.LengthStringNotValidException;
import ru.mfn.TableBuilder.payload.table.Request.CreateTableRequest;

public interface TableRepository {
    void create(CreateTableRequest createTableRequest) throws LengthStringNotValidException;
}
