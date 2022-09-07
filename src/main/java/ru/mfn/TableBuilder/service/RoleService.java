package ru.mfn.TableBuilder.service;

import ru.mfn.TableBuilder.payload.role.request.AddRoleRequest;
import ru.mfn.TableBuilder.payload.role.request.DeleteRoleRequest;
import ru.mfn.TableBuilder.payload.role.request.EditRoleRequest;
import ru.mfn.TableBuilder.payload.role.response.AddRoleResponse;
import ru.mfn.TableBuilder.payload.role.response.DeleteRoleResponse;
import ru.mfn.TableBuilder.payload.role.response.EditRoleResponse;
import ru.mfn.TableBuilder.payload.role.response.GetAllRoleResponse;

public interface RoleService {

    AddRoleResponse addRole(AddRoleRequest addRoleRequest);

    EditRoleResponse editRole(EditRoleRequest editRoleRequest);

    DeleteRoleResponse deleteRole(DeleteRoleRequest deleteRoleRequest);

    GetAllRoleResponse getAllRoles();
}
