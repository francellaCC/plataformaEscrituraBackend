package com.apirest.apirest.Controllers;

import com.apirest.apirest.Entities.User;
import com.apirest.apirest.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody User user, @AuthenticationPrincipal Jwt jwt){
        String email =  jwt.getClaimAsString("email");
        System.err.println(email);
        System.err.println(user);
        return ResponseEntity.ok(userService.createUser(email, user));
    }
}
