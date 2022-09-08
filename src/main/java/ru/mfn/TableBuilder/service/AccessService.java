package ru.mfn.TableBuilder.service;

import ru.mfn.TableBuilder.model.auth.Role;
import ru.mfn.TableBuilder.model.auth.User;
import ru.mfn.TableBuilder.service.exception.AccessDeniedException;
import ru.mfn.TableBuilder.service.exception.UserNotFoundException;

import java.security.Principal;
import java.util.Set;

public interface AccessService {

    void checkPermission(Principal principal, Set<Role> roles) throws AccessDeniedException, UserNotFoundException;

    Boolean checkAdmin(User user);

    User getUserByPrincipal(Principal principal) throws UserNotFoundException;

    Boolean checkAccess(User user,Set<Role> roles);

}
