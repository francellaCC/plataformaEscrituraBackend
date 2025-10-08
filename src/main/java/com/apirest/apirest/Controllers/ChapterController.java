package com.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Dtos.ChapterRequestDTO;
import com.apirest.apirest.Dtos.ChapterResponseDTO;
import com.apirest.apirest.Dtos.ChapterWithPagesDTO;
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

   @GetMapping("/getChapters/{storyId}")
   public ResponseEntity<List<ChapterResponseDTO>> getAllChapter(@PathVariable Long storyId,
         @AuthenticationPrincipal Jwt jwt) {

      String email = jwt.getClaimAsString("email");

      return ResponseEntity.ok(chapterService.getAllChapter(email, storyId));
   }

   @GetMapping("/getChapter/{storyId}/{chapterId}")
   public ResponseEntity<ChapterResponseDTO> getChapterById(@PathVariable Long storyId, @PathVariable Long chapterId,
         @AuthenticationPrincipal Jwt jwt) {
      String email = jwt.getClaimAsString("email");

      return ResponseEntity.ok(chapterService.getChapterById(email, storyId, chapterId));
   }

   @GetMapping("/{storyId}/first-chapter")
   public ResponseEntity<ChapterResponseDTO> getFirstChapter(@PathVariable Long storyId,
         @AuthenticationPrincipal Jwt jwt) {

      String email = jwt.getClaimAsString("email");
      return ResponseEntity.ok(chapterService.getChapterByStoryId(email, storyId));
   }
      @GetMapping("/next/{storyId}/{currentChapter}")
   public ResponseEntity<ChapterResponseDTO> getNextChapter(@PathVariable Long storyId,
         @AuthenticationPrincipal Jwt jwt, @PathVariable Long currentChapter) {

      String email = jwt.getClaimAsString("email");
      return ResponseEntity.ok(chapterService.getNextChapter(email, storyId, currentChapter));
   }

   @PutMapping("/update/{storyId}/{chapterId}")
   public ResponseEntity<ChapterResponseDTO> updateChapter(@PathVariable Long storyId, @PathVariable Long chapterId,
         @RequestBody ChapterRequestDTO chapterRequestDTO,
         @AuthenticationPrincipal Jwt jwt) {

      String email = jwt.getClaimAsString("email");
      return ResponseEntity.ok(chapterService.updateChapter(storyId, chapterId, chapterRequestDTO, email));
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity<Void> deleteStory(@PathVariable Long storyId, @PathVariable Long chapterId,
         @AuthenticationPrincipal Jwt jwt) {
      String email = jwt.getClaimAsString("email");

      return ResponseEntity.noContent().build();
   }
}
