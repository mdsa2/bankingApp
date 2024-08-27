package com.BankingApp.mohammed.User.Service;

import com.BankingApp.mohammed.User.Repositry.PermissionRepositry;
import com.BankingApp.mohammed.User.Repositry.UserRepositry;
import com.BankingApp.mohammed.User.Enum.Role;
import com.BankingApp.mohammed.User.Request.AuthenticationRequest;
import com.BankingApp.mohammed.User.Request.LoginRequest;
import com.BankingApp.mohammed.User.Request.RegisterRequest;
import com.BankingApp.mohammed.User.Response.AuthenticationResponse;
import com.BankingApp.mohammed.User.dto.GetAll;
import com.BankingApp.mohammed.User.dto.Mapper;
import com.BankingApp.mohammed.User.dto.UpdateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.BankingApp.mohammed.User.Entity.user;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepositry userRepositry;
    private final PermissionRepositry permissionRepositry;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

private final AuthenticationManager authenticationManager;
    public AuthenticationRequest register(RegisterRequest registerRequest) {
        var users = user.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.User)
                .build();
        userRepositry.save(users);
        var jwtToken = jwtService.generateToken(users);
        return AuthenticationRequest.builder()
                .token(jwtToken)
                .build();

    }

     public AuthenticationResponse authenticate (LoginRequest request){
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
         var user = userRepositry.findByEmail(request.getEmail())
                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));
          var jwtToken = jwtService.generateToken(user);
          return AuthenticationResponse.builder()
                    .token(jwtToken)
                  .build();

    }



    public List<GetAll> getAllUsers() {
        List<user> users = userRepositry.findAll();
        return users.stream()
                .map(Mapper::toGetAll)
                .collect(Collectors.toList());
    }
    public user updateUser(int userId, UpdateUserDto updateUserDto) {

        user users = userRepositry.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));



        users.setFirstName(updateUserDto.getFirstName());
        users.setLastName(updateUserDto.getLastName());
        users.setEmail(updateUserDto.getEmail());


        return userRepositry.save(users);
    }


}
