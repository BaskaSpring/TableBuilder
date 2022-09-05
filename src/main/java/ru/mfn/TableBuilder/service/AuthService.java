package ru.mfn.TableBuilder.service;

import ru.mfn.TableBuilder.payload.auth.request.SignUpRequest;

public interface AuthService {

    String registerUser(SignUpRequest signUpRequest);

}
