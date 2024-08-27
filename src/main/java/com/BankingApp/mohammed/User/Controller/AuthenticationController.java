package com.BankingApp.mohammed.User.Controller;

import com.BankingApp.mohammed.User.Entity.user;
import com.BankingApp.mohammed.User.Response.AuthenticationResponse;
import com.BankingApp.mohammed.User.Service.AuthenticationService;
import com.BankingApp.mohammed.User.Request.AuthenticationRequest;
import com.BankingApp.mohammed.User.Request.LoginRequest;
import com.BankingApp.mohammed.User.Request.RegisterRequest;
import com.BankingApp.mohammed.User.dto.GetAll;
import com.BankingApp.mohammed.User.dto.UpdateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationRequest> register(@RequestBody RegisterRequest request){
 return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> Login(@RequestBody LoginRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
    @GetMapping("/GetAll")
    public ResponseEntity<List<GetAll>> getAllUsers() {

        List<GetAll> users = authenticationService.getAllUsers();

        return ResponseEntity.ok(users);
    }
    @Secured("hasRole('admin')")
    @PutMapping("/update/{id}")
    public ResponseEntity<user> updateUser(
            @PathVariable int id,
            @RequestBody UpdateUserDto updateUserDto) {


        user updatedUser = authenticationService.updateUser(id, updateUserDto);


        return ResponseEntity.ok(updatedUser);
    }


/*    @PostMapping("/{userId}/permissions/{permissionId}")
    public user addPermissionToUser(@PathVariable int userId, @PathVariable int permissionId) {
        return authenticationService.addPermissionToUser(userId, permissionId);
    }

    @DeleteMapping("/{userId}/permissions/{permissionId}")
    public user removePermissionFromUser(@PathVariable int userId, @PathVariable int permissionId) {
        return authenticationService.removePermissionFromUser(userId, permissionId);
    }*/
}
