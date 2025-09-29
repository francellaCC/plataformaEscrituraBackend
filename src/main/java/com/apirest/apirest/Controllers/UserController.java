package com.apirest.apirest.Controllers;

import com.apirest.apirest.Dtos.UserRequestDTO;
import com.apirest.apirest.Dtos.UserResponseDTO;
import com.apirest.apirest.Entities.User;
import com.apirest.apirest.services.UserService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody User user, @AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getClaimAsString("email");

        try {
            UserResponseDTO response = userService.createUser(email, user);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getUser(@AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getClaimAsString("email");
        return ResponseEntity.ok(userService.getUser(email));
    }

    @PutMapping("/me/updateprofile")
    public ResponseEntity<UserResponseDTO> updateUser(@AuthenticationPrincipal Jwt jwt,
            @RequestBody UserRequestDTO dto) {
        String email = jwt.getClaimAsString("email");
        return ResponseEntity.ok(userService.updateUser(email, dto));
    }
}
