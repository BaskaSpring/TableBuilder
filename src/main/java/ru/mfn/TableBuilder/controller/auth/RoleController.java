package ru.mfn.TableBuilder.controller.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mfn.TableBuilder.model.auth.Role;
import ru.mfn.TableBuilder.payload.role.request.AddRoleRequest;
import ru.mfn.TableBuilder.payload.role.request.DeleteRoleRequest;
import ru.mfn.TableBuilder.payload.role.request.EditRoleRequest;
import ru.mfn.TableBuilder.repository.TableRepository;
import ru.mfn.TableBuilder.service.impl.AccessServiceImpl;
import ru.mfn.TableBuilder.service.impl.RoleServiceImpl;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role/1.0/")
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    AccessServiceImpl accessService;

    @Autowired
    TableRepository tableRepository;

    @PostMapping("/AddRole")
    public ResponseEntity<?> addRole(Principal principal, @Valid @RequestBody AddRoleRequest addRoleRequest) {
        Set<Role> roles =tableRepository.findByName("Role").get().getRoles();
        accessService.checkPermission(principal,roles);
        return ResponseEntity.ok(roleService.addRole(addRoleRequest));
    }


    @PutMapping("/EditRole")
    public ResponseEntity<?> editRole(Principal principal, @RequestBody @Valid EditRoleRequest editRoleRequest) {
        Set<Role> roles =tableRepository.findByName("Role").get().getRoles();
        accessService.checkPermission(principal,roles);
        return ResponseEntity.ok(roleService.editRole(editRoleRequest));
    }

    @DeleteMapping("/DeleteRole")
    public ResponseEntity<?> deleteRole(Principal principal, @Valid @RequestBody DeleteRoleRequest deleteRoleRequest) {
        Set<Role> roles =tableRepository.findByName("Role").get().getRoles();
        accessService.checkPermission(principal,roles);
        return ResponseEntity.ok(roleService.deleteRole(deleteRoleRequest));
    }

    @GetMapping("/GetAllRoles")
    public ResponseEntity<?> getAllRoles(Principal principal) {
//        Set<Role> roles =tableRepository.findByName("Role").get().getRoles();
//        accessService.checkPermission(principal,roles);
        return ResponseEntity.ok(roleService.getAllRoles());
    }

}
