package ru.mfn.TableBuilder.service.impl;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mfn.TableBuilder.service.exception.TableNameAlreadyExistException;
import ru.mfn.TableBuilder.model.core.Table;
import ru.mfn.TableBuilder.payload.table.Request.CreateTableRequest;
import ru.mfn.TableBuilder.payload.table.Request.EditTableRequest;
import ru.mfn.TableBuilder.repository.SupportTableRepository;
import ru.mfn.TableBuilder.repository.impl.TableRepositoryImpl;
import ru.mfn.TableBuilder.service.TableService;

import java.util.Optional;


@Service
public class TableServiceImpl implements TableService {

    @Autowired
    TableRepositoryImpl tableRepository;

    @Autowired
    SupportTableRepository supportTableRepository;

    private Boolean checkTableName(String name){
        Optional<Table> tableOptional = supportTableRepository.findByName(name);
        if (tableOptional.isEmpty()){
            return false;
        }
        return true;
    }


    @Override
    public void editTable(EditTableRequest editTableRequest) {

    }

    @Override

    public void createTable(CreateTableRequest createTableRequest) throws TableNameAlreadyExistException {
        if (checkTableName(createTableRequest.getName())) {
            throw new TableNameAlreadyExistException("Error: table name already use!");
        }
        tableRepository.create(createTableRequest);
    }
}
