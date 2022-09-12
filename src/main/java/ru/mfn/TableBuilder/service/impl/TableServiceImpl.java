package ru.mfn.TableBuilder.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mfn.TableBuilder.model.auth.Role;
import ru.mfn.TableBuilder.model.auth.User;
import ru.mfn.TableBuilder.payload.exception.LengthStringNotValidException;
import ru.mfn.TableBuilder.payload.table.Request.DeleteTableRequest;
import ru.mfn.TableBuilder.repository.RoleRepository;
import ru.mfn.TableBuilder.service.exception.*;
import ru.mfn.TableBuilder.model.core.Table;
import ru.mfn.TableBuilder.payload.table.Request.CreateTableRequest;
import ru.mfn.TableBuilder.payload.table.Request.EditTableRequest;
import ru.mfn.TableBuilder.repository.SupportTableRepository;
import ru.mfn.TableBuilder.repository.impl.TableRepositoryImpl;
import ru.mfn.TableBuilder.service.TableService;

import java.security.Principal;
import java.util.*;


@Service
public class TableServiceImpl implements TableService {

    @Autowired
    TableRepositoryImpl tableRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    SupportTableRepository supportTableRepository;

    @Autowired
    AccessServiceImpl accessService;

    private Boolean checkTableName(String name){
        Optional<Table> tableOptional = supportTableRepository.findByName(name.toUpperCase());
        if (tableOptional.isEmpty()){
            return false;
        }
        return true;
    }
    private void addTable(CreateTableRequest createTableRequest,User user) throws RoleNotFoundException {
        Set<String> strRoles = createTableRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        List<Role> roleList = roleRepository.findAllWithoutAdminAndModerator();
        int count  = 0;
        for (int i = 0; i <roleList.size(); i++) {
            Iterator<String> iterator = strRoles.iterator();
            while (iterator.hasNext()) {
                if (roleList.get(i).getName().equals(iterator.next())){
                    count++;
                    roles.add(roleList.get(i));
                }
            }
        }
        if (strRoles.size()!=count){
            throw new RoleNotFoundException("Error: Role is not found!");
        }
        Table table = new Table();
        table.setDivision(createTableRequest.getDivision());
        table.setEnabled(true);
        table.setSystemTable(false);
        table.setTableName(createTableRequest.getName().toUpperCase());
        table.setRoles(roles);
        table.setOwner(user);
        supportTableRepository.save(table);
    }


    @Override
    public void editTable(EditTableRequest editTableRequest) {

    }

    @Override
    public void deleteTable(Principal principal,DeleteTableRequest deleteTableRequest) throws TableNotFoundException, UserNotFoundException, AccessDeniedException {
        User user = accessService.getUserByPrincipal(principal);
        Optional<Table> tableOptional = supportTableRepository.findById(deleteTableRequest.getId());
        if (tableOptional.isEmpty()) {
            throw new TableNotFoundException("Error: table not found!");
        }
        Table table = tableOptional.get();
        if (accessService.checkAdministrator(principal)||table.getOwner()==user) {
            table.setEnabled(false);
            supportTableRepository.save(table);
        } else{
            throw new AccessDeniedException("Error: access denied!");
        }
    }

    @Override
    public void createTable(Principal principal,CreateTableRequest createTableRequest) throws TableNameAlreadyExistException, LengthStringNotValidException, RoleNotFoundException, UserNotFoundException {
        User user = accessService.getUserByPrincipal(principal);
        if (checkTableName(createTableRequest.getName())) {
            throw new TableNameAlreadyExistException("Error: table name already use!");
        }
        addTable(createTableRequest,user);
        tableRepository.create(createTableRequest);
    }
}
