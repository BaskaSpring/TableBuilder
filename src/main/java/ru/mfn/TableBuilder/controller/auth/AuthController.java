package ru.mfn.TableBuilder.controller.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mfn.TableBuilder.payload.auth.request.SignUpRequest;
import ru.mfn.TableBuilder.service.impl.AuthServiceImpl;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthServiceImpl authServiceImpl;


    @PostMapping("/SignUp")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return  ResponseEntity.ok(authServiceImpl.registerUser(signUpRequest));
    }
}
