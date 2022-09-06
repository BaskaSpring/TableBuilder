package ru.mfn.TableBuilder.service.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mfn.TableBuilder.exception.RoleAlreadyExistException;
import ru.mfn.TableBuilder.model.auth.Role;
import ru.mfn.TableBuilder.payload.role.request.AddRoleRequest;
import ru.mfn.TableBuilder.payload.role.response.AddRoleResponse;
import ru.mfn.TableBuilder.repository.RoleRepository;
import ru.mfn.TableBuilder.service.RoleService;

import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    RoleRepository roleRepository;

    @Override
    @SneakyThrows
    public AddRoleResponse addRole(AddRoleRequest addRoleRequest) {
        Optional<Role> optionalRole = roleRepository.findByName(addRoleRequest.getName());
        if (optionalRole.isPresent()){
            throw new RoleAlreadyExistException("Error: Role already exist!");
        }
        Role role  = new Role();
        role.setName(addRoleRequest.getName());
        role = roleRepository.save(role);
        AddRoleResponse response = new AddRoleResponse();
        response.setId(role.getId());
        response.setName(role.getName());
        return response;
    }


}
