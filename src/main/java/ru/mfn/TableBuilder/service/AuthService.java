package ru.mfn.TableBuilder.service;

import ru.mfn.TableBuilder.payload.auth.response.*;
import ru.mfn.TableBuilder.payload.auth.request.*;


public interface AuthService {

    String registerUser(SignUpRequest signUpRequest);

    JwtResponse authenticateUser(LoginRequest loginRequest);

    TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest);

    MessageResponse logoutUser(LogOutRequest logOutRequest);
}
