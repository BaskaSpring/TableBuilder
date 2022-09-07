package ru.mfn.TableBuilder.service.impl;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mfn.TableBuilder.exception.AccessDeniedException;
import ru.mfn.TableBuilder.exception.UserNotFoundException;
import ru.mfn.TableBuilder.model.auth.ERole;
import ru.mfn.TableBuilder.model.auth.Role;
import ru.mfn.TableBuilder.model.auth.User;
import ru.mfn.TableBuilder.repository.RoleRepository;
import ru.mfn.TableBuilder.repository.UserRepository;
import ru.mfn.TableBuilder.service.AccessService;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@Service
public class AccessServiceImpl implements AccessService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @SneakyThrows
    public User getUserByPrincipal(Principal principal) {
        Optional<User> userOptional = userRepository.findByUsername(principal.getName());
        if (userOptional.isEmpty()){
            throw new UserNotFoundException("Error: User not found!");
        }
        return userOptional.get();
    }

    @Override
    public Boolean checkAccess(User user,Set<Role> roles) {
        return user.getRoles().stream().anyMatch(roles::contains);
    }

    @Override
    public Boolean checkAdmin(User user) {
        return user.getRoles().stream().anyMatch(roleRepository.findByRole(ERole.ADMIN)::contains);
    }

    @Override
    @SneakyThrows
    public void checkPermission(Principal principal, Set<Role> roles) {
        User user = getUserByPrincipal(principal);
        if (checkAdmin(user)) {
            return;
        }
        if (checkAccess(user,roles)) {
            return;
        }
        throw new AccessDeniedException("Error: Access denied!");
    }
}
