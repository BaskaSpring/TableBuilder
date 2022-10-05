package ru.mfn.TableBuilder.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mfn.TableBuilder.model.auth.Role;
import ru.mfn.TableBuilder.payload.role.request.AddRoleRequest;
import ru.mfn.TableBuilder.payload.role.request.CheckRoleRequest;
import ru.mfn.TableBuilder.payload.role.request.DeleteRoleRequest;
import ru.mfn.TableBuilder.payload.role.request.EditRoleRequest;
import ru.mfn.TableBuilder.repository.SupportTableRepository;
import ru.mfn.TableBuilder.service.exception.AccessDeniedException;
import ru.mfn.TableBuilder.service.exception.RoleAlreadyExistException;
import ru.mfn.TableBuilder.service.exception.RoleNotFoundException;
import ru.mfn.TableBuilder.service.exception.UserNotFoundException;
import ru.mfn.TableBuilder.service.impl.AccessServiceImpl;
import ru.mfn.TableBuilder.service.impl.RoleServiceImpl;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role/1.0/")
public class RoleController {

    final
    RoleServiceImpl roleService;

    final
    AccessServiceImpl accessService;

    final
    SupportTableRepository supportTableRepository;

    public RoleController(RoleServiceImpl roleService, AccessServiceImpl accessService, SupportTableRepository supportTableRepository) {
        this.roleService = roleService;
        this.accessService = accessService;
        this.supportTableRepository = supportTableRepository;
    }

    @PostMapping("/AddRole")
    public ResponseEntity<?> addRole(Principal principal, @Valid @RequestBody AddRoleRequest addRoleRequest)
            throws AccessDeniedException, UserNotFoundException, RoleAlreadyExistException {
        Set<Role> roles = supportTableRepository.findByName("Role").get().getRoles();
        accessService.checkPermission(principal,roles);
        return ResponseEntity.ok(roleService.addRole(addRoleRequest));
    }

    @PostMapping("/CheckRoleName")
    public ResponseEntity<?> checkRole(@Valid @RequestBody CheckRoleRequest checkRoleRequest) {
        return ResponseEntity.ok(roleService.checkRole(checkRoleRequest));
    }


    @PutMapping("/EditRole")
    public ResponseEntity<?> editRole(Principal principal, @RequestBody @Valid EditRoleRequest editRoleRequest)
            throws AccessDeniedException, UserNotFoundException, RoleAlreadyExistException, RoleNotFoundException {
        Set<Role> roles = supportTableRepository.findByName("Role").get().getRoles();
        accessService.checkPermission(principal,roles);
        return ResponseEntity.ok(roleService.editRole(editRoleRequest));
    }

    @DeleteMapping("/DeleteRole")
    public ResponseEntity<?> deleteRole(Principal principal, @Valid @RequestBody DeleteRoleRequest deleteRoleRequest)
            throws RoleNotFoundException, AccessDeniedException, UserNotFoundException {
        Set<Role> roles = supportTableRepository.findByName("ROLE").get().getRoles();
        accessService.checkPermission(principal,roles);
        return ResponseEntity.ok(roleService.deleteRole(deleteRoleRequest));
    }

    @GetMapping("/GetAllRoles")
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

}
