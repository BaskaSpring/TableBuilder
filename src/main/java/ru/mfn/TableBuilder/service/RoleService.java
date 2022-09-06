package ru.mfn.TableBuilder.service;

import ru.mfn.TableBuilder.payload.role.request.AddRoleRequest;
import ru.mfn.TableBuilder.payload.role.response.AddRoleResponse;

public interface RoleService {

    AddRoleResponse addRole(AddRoleRequest addRoleRequest);
}
