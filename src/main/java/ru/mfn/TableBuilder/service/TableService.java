package ru.mfn.TableBuilder.service;

import ru.mfn.TableBuilder.payload.exception.LengthStringNotValidException;
import ru.mfn.TableBuilder.payload.table.Request.CreateTableRequest;
import ru.mfn.TableBuilder.payload.table.Request.DeleteTableRequest;
import ru.mfn.TableBuilder.payload.table.Request.EditTableRequest;
import ru.mfn.TableBuilder.service.exception.*;

import java.security.Principal;

public interface TableService {

    void createTable(Principal principal,CreateTableRequest createTableRequest) throws TableNameAlreadyExistException, LengthStringNotValidException, RoleNotFoundException, UserNotFoundException;

    void editTable(EditTableRequest editTableRequest);

    void deleteTable(Principal principal,DeleteTableRequest deleteTableRequest) throws TableNotFoundException, UserNotFoundException, AccessDeniedException;

}
