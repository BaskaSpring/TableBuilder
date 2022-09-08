package ru.mfn.TableBuilder.controller.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mfn.TableBuilder.payload.auth.request.LogOutRequest;
import ru.mfn.TableBuilder.payload.auth.request.LoginRequest;
import ru.mfn.TableBuilder.payload.auth.request.SignUpRequest;
import ru.mfn.TableBuilder.payload.auth.request.TokenRefreshRequest;
import ru.mfn.TableBuilder.security.exception.EmailAlreadyUseException;
import ru.mfn.TableBuilder.security.exception.RefreshTokenExpired;
import ru.mfn.TableBuilder.security.exception.RefreshTokenNotDatabase;
import ru.mfn.TableBuilder.service.exception.RoleNotFoundException;
import ru.mfn.TableBuilder.service.exception.UserNotActiveException;
import ru.mfn.TableBuilder.service.exception.UserNotFoundException;
import ru.mfn.TableBuilder.service.impl.AuthServiceImpl;

import javax.validation.Valid;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/1.0/")
public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    @PostMapping("/SignUp")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest)
            throws RoleNotFoundException, EmailAlreadyUseException {
        return ResponseEntity.ok(authService.registerUser(signUpRequest));
    }

    @PostMapping("/SignIn")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
            throws UserNotFoundException, UserNotActiveException {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/RefreshToken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest tokenRefreshRequest)
            throws RefreshTokenExpired, RefreshTokenNotDatabase {
        return ResponseEntity.ok(authService.refreshToken(tokenRefreshRequest));
    }

    @PostMapping("/Logout")
    public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
        return ResponseEntity.ok(authService.logoutUser(logOutRequest));
    }
}
