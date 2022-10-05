package ru.mfn.TableBuilder.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mfn.TableBuilder.payload.exception.LengthStringNotValidException;
import ru.mfn.TableBuilder.payload.role.request.EditRoleRequest;
import ru.mfn.TableBuilder.payload.table.Request.CreateTableRequest;
import ru.mfn.TableBuilder.payload.table.Request.DeleteTableRequest;
import ru.mfn.TableBuilder.service.exception.*;
import ru.mfn.TableBuilder.service.impl.AccessServiceImpl;
import ru.mfn.TableBuilder.service.impl.TableServiceImpl;

import javax.validation.Valid;
import java.security.Principal;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/table/1.0/")
public class TableController {

    final
    TableServiceImpl tableService;

    final
    AccessServiceImpl accessService;

    public TableController(TableServiceImpl tableService, AccessServiceImpl accessService) {
        this.tableService = tableService;
        this.accessService = accessService;
    }


    @PostMapping("/AddTable")
    public ResponseEntity<?> addTable(Principal principal, @Valid @RequestBody CreateTableRequest createTableRequest)
            throws TableNameAlreadyExistException, LengthStringNotValidException, RoleNotFoundException, UserNotFoundException, AccessDeniedException {
        accessService.checkPermissionEditTable(principal);
        tableService.createTable(principal,createTableRequest);
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/EditTable")
    public ResponseEntity<?> editTable(Principal principal, @RequestBody @Valid EditRoleRequest editRoleRequest) {
//        Set<Role> roles = supportTableRepository.findByName("Role").get().getRoles();
//        accessService.checkPermission(principal,roles)
//        tableService.
        return ResponseEntity.ok("ok");
    }


    @DeleteMapping("/DeleteTable")
    public ResponseEntity<?> deleteTable(Principal principal, @RequestBody @Valid DeleteTableRequest deleteTableRequest)
            throws UserNotFoundException, TableNotFoundException, AccessDeniedException {
        tableService.deleteTable(principal,deleteTableRequest);
        return ResponseEntity.ok("ok");
    }
}


