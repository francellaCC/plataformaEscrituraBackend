package com.apirest.apirest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Dtos.ChapterRequestDTO;
import com.apirest.apirest.Dtos.ChapterResponseDTO;
import com.apirest.apirest.services.ChapterService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/chapter")
public class ChapterController {

   @Autowired
   private ChapterService chapterService;

   @PostMapping("/create/{storyId}")
   public ResponseEntity<ChapterResponseDTO> createChapter(@PathVariable Long storyId,
         @RequestBody ChapterRequestDTO chapterRequestDTO,
         @AuthenticationPrincipal Jwt jwt) {
      String email = jwt.getClaimAsString("email");

      return ResponseEntity.ok(chapterService.createChapter(chapterRequestDTO, email, storyId));
   }

}
