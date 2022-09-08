package ru.mfn.TableBuilder.service;

import ru.mfn.TableBuilder.payload.role.request.AddRoleRequest;
import ru.mfn.TableBuilder.payload.role.request.CheckRoleRequest;
import ru.mfn.TableBuilder.payload.role.request.DeleteRoleRequest;
import ru.mfn.TableBuilder.payload.role.request.EditRoleRequest;
import ru.mfn.TableBuilder.payload.role.response.AddRoleResponse;
import ru.mfn.TableBuilder.payload.role.response.DeleteRoleResponse;
import ru.mfn.TableBuilder.payload.role.response.EditRoleResponse;
import ru.mfn.TableBuilder.payload.role.response.GetAllRoleResponse;
import ru.mfn.TableBuilder.service.exception.RoleAlreadyExistException;
import ru.mfn.TableBuilder.service.exception.RoleNotFoundException;

public interface RoleService {

    AddRoleResponse addRole(AddRoleRequest addRoleRequest) throws RoleAlreadyExistException;

    EditRoleResponse editRole(EditRoleRequest editRoleRequest) throws RoleAlreadyExistException, RoleNotFoundException;

    DeleteRoleResponse deleteRole(DeleteRoleRequest deleteRoleRequest) throws RoleNotFoundException;

    GetAllRoleResponse getAllRoles();

    Boolean checkRole(CheckRoleRequest checkRoleRequest);
}
