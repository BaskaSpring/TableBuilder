package ru.mfn.TableBuilder.service;

import ru.mfn.TableBuilder.model.auth.Role;
import ru.mfn.TableBuilder.model.auth.User;

import java.security.Principal;
import java.util.Set;

public interface AccessService {

    void checkPermission(Principal principal, Set<Role> roles);

    Boolean checkAdmin(User user);

    User getUserByPrincipal(Principal principal);

    Boolean checkAccess(User user,Set<Role> roles);

}
