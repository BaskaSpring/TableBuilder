package ru.mfn.TableBuilder.service;

import ru.mfn.TableBuilder.model.auth.Role;

import java.security.Principal;
import java.util.Set;

public interface AccessService {

    void checkPermission(Principal principal, Set<Role> roles);

}
