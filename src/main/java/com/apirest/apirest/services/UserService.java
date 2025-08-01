package com.apirest.apirest.services;

import com.apirest.apirest.Entities.User;
import com.apirest.apirest.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> createUser(String email, User user){
        Optional<User> existingUser = userRepository.findByEmail(email);

        if(existingUser.isEmpty()){
 //            No exisate usuario entonces se crea
            user.setEmail(email);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Usuario registrado exitosamente"));

        }else{
//            si existe
            if(existingUser.get().getEmail().equals(email)){
                return ResponseEntity.ok(Map.of("message", "Acceso permitido. Usuario ya registrado"));
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido para este usuario");
            }
        }

    }
}
