package ru.mfn.TableBuilder.controller.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mfn.TableBuilder.model.auth.Role;
import ru.mfn.TableBuilder.payload.role.request.AddRoleRequest;
import ru.mfn.TableBuilder.service.impl.AccessServiceImpl;
import ru.mfn.TableBuilder.service.impl.RoleServiceImpl;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role/1.0/")
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    AccessServiceImpl accessService;

    @PostMapping("/AddRole")
    public ResponseEntity<?> addRole(Principal principal, @Valid @RequestBody AddRoleRequest addRoleRequest) {

        Set<Role> roles = new HashSet<>();

        accessService.checkPermission(principal,roles);

        int i = 1;

        return ResponseEntity.ok(roleService.addRole(addRoleRequest));
    }


//    @PutMapping("/ModifyRole")
//    public ResponseEntity<?> modifyRole(@Valid @RequestBody ModifyRoleRequest modifyRoleRequest) {
//        return ResponseEntity.ok(roleService.addRole(addRoleRequest));
//    }
}
