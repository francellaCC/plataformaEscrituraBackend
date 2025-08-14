package com.apirest.apirest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Dtos.PageRequestDTO;
import com.apirest.apirest.Dtos.PageResponseDTO;
import com.apirest.apirest.services.PageService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/pages")
public class PageController {

   @Autowired
   private PageService pageService;

   @PostMapping("/createPage/{storyId}/{chapterId}")
   public ResponseEntity<PageResponseDTO> createPage(@PathVariable Long storyId, @PathVariable Long chapterId,
         @AuthenticationPrincipal Jwt jwt, @RequestBody PageRequestDTO dto) {
      String email = jwt.getClaimAsString("email");

      System.err.println("JSON recibido: " + dto); 
      return ResponseEntity.ok(pageService.createPage(storyId, chapterId, dto, email));

   }
}
