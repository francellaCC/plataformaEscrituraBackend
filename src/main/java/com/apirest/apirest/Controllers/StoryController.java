package com.apirest.apirest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Dtos.StoryRequestDTO;
import com.apirest.apirest.Dtos.StoryResponseDTO;
import com.apirest.apirest.services.StoryService;
import org.springframework.security.oauth2.jwt.Jwt;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/stories")
public class StoryController {
   
   @Autowired
   private StoryService storyService;

   @PostMapping("/createStory")
   public ResponseEntity<StoryResponseDTO> createStory(@RequestBody StoryRequestDTO dto, @AuthenticationPrincipal Jwt jwt){
     String email =  jwt.getClaimAsString("email");
     return ResponseEntity.ok(storyService.createStory(dto, email));
   }
}
