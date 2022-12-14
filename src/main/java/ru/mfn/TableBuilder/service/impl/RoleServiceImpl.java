package ru.mfn.TableBuilder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mfn.TableBuilder.service.exception.RoleAlreadyExistException;
import ru.mfn.TableBuilder.service.exception.RoleNotFoundException;
import ru.mfn.TableBuilder.model.auth.ERole;
import ru.mfn.TableBuilder.model.auth.Role;
import ru.mfn.TableBuilder.payload.role.request.AddRoleRequest;
import ru.mfn.TableBuilder.payload.role.request.CheckRoleRequest;
import ru.mfn.TableBuilder.payload.role.request.DeleteRoleRequest;
import ru.mfn.TableBuilder.payload.role.request.EditRoleRequest;
import ru.mfn.TableBuilder.payload.role.response.AddRoleResponse;
import ru.mfn.TableBuilder.payload.role.response.DeleteRoleResponse;
import ru.mfn.TableBuilder.payload.role.response.EditRoleResponse;
import ru.mfn.TableBuilder.payload.role.response.GetAllRoleResponse;
import ru.mfn.TableBuilder.repository.RoleRepository;
import ru.mfn.TableBuilder.service.RoleService;

import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService {


    final
    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Boolean checkRole(CheckRoleRequest checkRoleRequest) {
        Optional<Role> roleOptional = roleRepository.findByName(checkRoleRequest.getName().toUpperCase());
        if (roleOptional.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public GetAllRoleResponse getAllRoles() {
        GetAllRoleResponse response = new GetAllRoleResponse();
        response.setRoleList(roleRepository.findAll());
        return response;
    }

    @Override
    public DeleteRoleResponse deleteRole(DeleteRoleRequest deleteRoleRequest) throws RoleNotFoundException {
        Optional<Role> roleOptional = roleRepository.findById(deleteRoleRequest.getId());
        if (roleOptional.isEmpty()) {
            throw new RoleNotFoundException("Error: Role not found!");
        }
        Role role = roleOptional.get();
        role.setEnabled(false);
        roleRepository.save(role);
        DeleteRoleResponse response = new DeleteRoleResponse();
        response.setMessage("Role delete successful");
        return response;
    }

    @Override
    public AddRoleResponse addRole(AddRoleRequest addRoleRequest) throws RoleAlreadyExistException {
        Optional<Role> optionalRole = roleRepository.findByName(addRoleRequest.getName());
        if (optionalRole.isPresent()){
            throw new RoleAlreadyExistException("Error: Role already exist!");
        }
        Role role  = new Role();
        role.setName(addRoleRequest.getName().toUpperCase());
        role.setERole(ERole.USER);
        role.setEnabled(true);
        role = roleRepository.save(role);
        AddRoleResponse response = new AddRoleResponse();
        response.setId(role.getId());
        response.setName(role.getName());
        return response;
    }

    @Override
    public EditRoleResponse editRole(EditRoleRequest editRoleRequest) throws RoleAlreadyExistException, RoleNotFoundException {
        Optional<Role> optionalRole = roleRepository.findByName(editRoleRequest.getName().toUpperCase());
        if (optionalRole.isPresent()){
            throw new RoleAlreadyExistException("Error: Role already exist!");
        }
        Optional<Role> roleOptional = roleRepository.findById(editRoleRequest.getId());
        if (roleOptional.isEmpty()) {
            throw new RoleNotFoundException("Error: Role not found!");
        }
        Role role = roleOptional.get();
        role.setName(editRoleRequest.getName().toUpperCase());
        role = roleRepository.save(role);
        EditRoleResponse response = new EditRoleResponse();
        response.setId(role.getId());
        response.setName(role.getName());
        return response;
    }
}
