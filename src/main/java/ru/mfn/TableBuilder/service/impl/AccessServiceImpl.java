package ru.mfn.TableBuilder.service.impl;


import org.springframework.stereotype.Service;
import ru.mfn.TableBuilder.model.auth.Role;
import ru.mfn.TableBuilder.service.AccessService;

import java.security.Principal;
import java.util.Set;

@Service
public class AccessServiceImpl implements AccessService {

    @Override
    public void checkPermission(Principal principal, Set<Role> roles) {

    }
}
