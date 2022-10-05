package ru.mfn.TableBuilder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mfn.TableBuilder.service.exception.AccessDeniedException;
import ru.mfn.TableBuilder.service.exception.UserNotFoundException;
import ru.mfn.TableBuilder.model.auth.ERole;
import ru.mfn.TableBuilder.model.auth.Role;
import ru.mfn.TableBuilder.model.auth.User;
import ru.mfn.TableBuilder.repository.RoleRepository;
import ru.mfn.TableBuilder.repository.UserRepository;
import ru.mfn.TableBuilder.service.AccessService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccessServiceImpl implements AccessService {

    final
    UserRepository userRepository;

    final
    RoleRepository roleRepository;

    public AccessServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User getUserByPrincipal(Principal principal) throws UserNotFoundException {
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


    public Boolean checkAdmin(User user) {
        return user.getRoles().stream().anyMatch(roleRepository.findByRole(ERole.ADMIN)::contains);
    }

    @Override
    public Boolean checkAdministrator(Principal principal) throws UserNotFoundException {
        return checkAdmin(getUserByPrincipal(principal));
    }

    @Override
    public void checkPermission(Principal principal, Set<Role> roles) throws AccessDeniedException, UserNotFoundException {
        User user = getUserByPrincipal(principal);
        if (checkAdmin(user)) {
            return;
        }
        if (checkAccess(user,roles)) {
            return;
        }
        throw new AccessDeniedException("Error: Access denied!");
    }


    @Override
    public void checkPermissionEditTable(Principal principal) throws AccessDeniedException, UserNotFoundException {
        User user = getUserByPrincipal(principal);
        if (checkAdmin(user)) {
            return;
        }
        List<Role> roles = roleRepository.findByRole(ERole.MODERATOR);
        if (checkAccess(user,new HashSet<>(roles))) {
            return;
        }
        throw new AccessDeniedException("Error: Access denied!");
    }
}
