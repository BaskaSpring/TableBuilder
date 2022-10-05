package ru.mfn.TableBuilder.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mfn.TableBuilder.model.auth.RefreshToken;
import ru.mfn.TableBuilder.model.auth.Role;
import ru.mfn.TableBuilder.model.auth.User;
import ru.mfn.TableBuilder.payload.auth.request.LogOutRequest;
import ru.mfn.TableBuilder.payload.auth.request.LoginRequest;
import ru.mfn.TableBuilder.payload.auth.request.SignUpRequest;
import ru.mfn.TableBuilder.payload.auth.request.TokenRefreshRequest;
import ru.mfn.TableBuilder.payload.auth.response.JwtResponse;
import ru.mfn.TableBuilder.payload.auth.response.MessageResponse;
import ru.mfn.TableBuilder.payload.auth.response.TokenRefreshResponse;
import ru.mfn.TableBuilder.repository.*;
import ru.mfn.TableBuilder.security.exception.EmailAlreadyUseException;
import ru.mfn.TableBuilder.security.exception.RefreshTokenExpired;
import ru.mfn.TableBuilder.security.exception.RefreshTokenNotDatabase;
import ru.mfn.TableBuilder.security.jwt.JwtUtils;
import ru.mfn.TableBuilder.security.services.RefreshTokenService;
import ru.mfn.TableBuilder.security.services.UserDetailsImpl;
import ru.mfn.TableBuilder.service.AuthService;
import ru.mfn.TableBuilder.service.exception.RoleNotFoundException;
import ru.mfn.TableBuilder.service.exception.UserNotActiveException;
import ru.mfn.TableBuilder.service.exception.UserNotFoundException;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    AuthenticationManager authenticationManager;

    UserRepository userRepository;

    RoleRepository roleRepository;

    PasswordEncoder encoder;

    JwtUtils jwtUtils;

    RefreshTokenService refreshTokenService;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public String registerUser(SignUpRequest signUpRequest) throws EmailAlreadyUseException, RoleNotFoundException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new EmailAlreadyUseException("Error: Username is already taken!");
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailAlreadyUseException("Error: Email is already in use!");
        }
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        List<Role> roleList = roleRepository.findAll();

        int count  = 0;

        for (int i = 0; i <roleList.size(); i++) {
            Iterator<String> iterator = strRoles.iterator();
            while (iterator.hasNext()) {
                if (roleList.get(i).getName().equals(iterator.next())){
                    count++;
                    roles.add(roleList.get(i));
                }
            }
        }
        if (strRoles.size()!=count){
            throw new RoleNotFoundException("Error: Role is not found!");
        }

        user.setRoles(roles);
        userRepository.save(user);

        return "User registered successfully!";
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) throws UserNotFoundException, UserNotActiveException {
        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());
        if (optionalUser.isEmpty()){
            throw new UserNotFoundException("Error: User not found!");
        }
        User user = optionalUser.get();
        if (!user.getEnabled()){
            throw new UserNotActiveException("Error: User not active!");
        }
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }

    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) throws RefreshTokenNotDatabase, RefreshTokenExpired {
        TokenRefreshResponse tokenRefreshResponse = new TokenRefreshResponse();
        Optional<RefreshToken> optionalRefreshToken = refreshTokenService.findByToken(tokenRefreshRequest.getRefreshToken());
        if (optionalRefreshToken.isEmpty()){
            throw new RefreshTokenNotDatabase("Error: Refresh token is not find or expired!");
        }
        RefreshToken refreshToken = optionalRefreshToken.get();
        if (!refreshTokenService.verifyExpiration(refreshToken)){
           throw new RefreshTokenExpired("Error: Refresh token expired!");
        }
        tokenRefreshResponse.setAccessToken(jwtUtils.generateTokenFromUsername(refreshToken.getUser().getUsername()));
        tokenRefreshResponse.setRefreshToken(refreshTokenService.createRefreshToken(refreshToken.getUser().getId()).getToken());
        return tokenRefreshResponse;
    }

    @Override
    public MessageResponse logoutUser(LogOutRequest logOutRequest) {
        refreshTokenService.deleteByUserId(logOutRequest.getUserId());
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Log out successful!");
        return messageResponse;
    }
}
