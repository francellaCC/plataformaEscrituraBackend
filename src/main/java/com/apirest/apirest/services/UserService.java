package com.apirest.apirest.services;

import com.apirest.apirest.Dtos.UserRequestDTO;
import com.apirest.apirest.Dtos.UserResponseDTO;
import com.apirest.apirest.Entities.User;
import com.apirest.apirest.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO createUser(String email, User user) {
        Optional<User> existingUser = userRepository.findByEmail(email);

       if (existingUser.isEmpty()) {
        user.setEmail(email);
        User saved = userRepository.save(user);
        return new UserResponseDTO(saved);
    } else {
        if (existingUser.get().getEmail().equals(email)) {
            return new UserResponseDTO(existingUser.get());
        } else {
            throw new RuntimeException("Token inválido para este usuario");
        }
    }
    }

    public UserResponseDTO getUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserResponseDTO(user);
    }

    public UserResponseDTO updateUser(String email, UserRequestDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user.getId().equals(dto.getId())) {
            user.setNameUser(dto.getName());
            user.setPicture(dto.getPicture());

            return new UserResponseDTO(userRepository.save(user));
        } else {
            return null;
        }

    }
}
