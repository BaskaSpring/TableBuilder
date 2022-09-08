package ru.mfn.TableBuilder.service;

import ru.mfn.TableBuilder.payload.table.Request.CreateTableRequest;
import ru.mfn.TableBuilder.payload.table.Request.EditTableRequest;
import ru.mfn.TableBuilder.service.exception.TableNameAlreadyExistException;

public interface TableService {

    void createTable(CreateTableRequest createTableRequest) throws TableNameAlreadyExistException;

    void editTable(EditTableRequest editTableRequest);
}
