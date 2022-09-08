package ru.mfn.TableBuilder.service;

import ru.mfn.TableBuilder.payload.auth.response.*;
import ru.mfn.TableBuilder.payload.auth.request.*;
import ru.mfn.TableBuilder.security.exception.EmailAlreadyUseException;
import ru.mfn.TableBuilder.security.exception.RefreshTokenExpired;
import ru.mfn.TableBuilder.security.exception.RefreshTokenNotDatabase;
import ru.mfn.TableBuilder.service.exception.RoleNotFoundException;
import ru.mfn.TableBuilder.service.exception.UserNotActiveException;
import ru.mfn.TableBuilder.service.exception.UserNotFoundException;


public interface AuthService {

    String registerUser(SignUpRequest signUpRequest) throws EmailAlreadyUseException, RoleNotFoundException;

    JwtResponse authenticateUser(LoginRequest loginRequest) throws UserNotFoundException, UserNotActiveException;

    TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) throws RefreshTokenNotDatabase, RefreshTokenExpired;

    MessageResponse logoutUser(LogOutRequest logOutRequest);
}
