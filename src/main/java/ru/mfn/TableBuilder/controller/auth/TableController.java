package ru.mfn.TableBuilder.controller.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mfn.TableBuilder.model.auth.Role;
import ru.mfn.TableBuilder.payload.role.request.EditRoleRequest;
import ru.mfn.TableBuilder.payload.table.Request.CreateTableRequest;
import ru.mfn.TableBuilder.repository.SupportTableRepository;
import ru.mfn.TableBuilder.service.exception.TableNameAlreadyExistException;
import ru.mfn.TableBuilder.service.impl.AccessServiceImpl;
import ru.mfn.TableBuilder.service.impl.TableServiceImpl;

import javax.validation.Valid;
import java.security.Principal;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/table/1.0/")
public class TableController {

    @Autowired
    TableServiceImpl tableService;

    @Autowired
    AccessServiceImpl accessService;

    @Autowired
    SupportTableRepository supportTableRepository;

    @PostMapping("/AddTable")
    public ResponseEntity<?> addTable(Principal principal, @Valid @RequestBody CreateTableRequest createTableRequest)
            throws TableNameAlreadyExistException {
        accessService.checkPermission(principal,roles);
        tableService.createTable(createTableRequest);
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/EditTable")
    public ResponseEntity<?> editTable(Principal principal, @RequestBody @Valid EditRoleRequest editRoleRequest) {
//        Set<Role> roles = supportTableRepository.findByName("Role").get().getRoles();
//        accessService.checkPermission(principal,roles)
//        tableService.
        return ResponseEntity.ok("ok");
    }

}
