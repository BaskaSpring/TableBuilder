package ru.mfn.TableBuilder.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mfn.TableBuilder.exception.EmailAlreadyUseException;
import ru.mfn.TableBuilder.exception.RoleNotFoundException;
import ru.mfn.TableBuilder.model.auth.Role;
import ru.mfn.TableBuilder.model.auth.User;
import ru.mfn.TableBuilder.payload.auth.request.SignUpRequest;
import ru.mfn.TableBuilder.repository.*;
import ru.mfn.TableBuilder.service.AuthService;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    @SneakyThrows
    public String registerUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new EmailAlreadyUseException("Error: Username is already taken!");
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailAlreadyUseException("Error: Email is already in use!");
        }
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
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


}
