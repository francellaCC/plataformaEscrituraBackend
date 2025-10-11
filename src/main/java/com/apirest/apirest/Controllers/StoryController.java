package com.apirest.apirest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Dtos.StoryRequestDTO;
import com.apirest.apirest.Dtos.StoryResponseDTO;
import com.apirest.apirest.Dtos.StoryWithAuthorDTO;
import com.apirest.apirest.services.StoryService;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.List;

@RestController
@RequestMapping("/api/stories")
public class StoryController {

  @Autowired
  private StoryService storyService;

  @PostMapping("/createStory")
  public ResponseEntity<StoryResponseDTO> createStory(@RequestBody StoryRequestDTO dto,
      @AuthenticationPrincipal Jwt jwt) {
    String email = jwt.getClaimAsString("email");
    return ResponseEntity.ok(storyService.createStory(dto, email));
  }

  @GetMapping("/myStories")
  public ResponseEntity<List<StoryResponseDTO>> getMyStories(@AuthenticationPrincipal Jwt jwt) {
    String email = jwt.getClaimAsString("email");
    return ResponseEntity.ok(storyService.getUserStories(email));
  }

  @GetMapping("/myStory/{id}")
  public ResponseEntity<StoryResponseDTO> getStoryById(@PathVariable Long id) {
    StoryResponseDTO story = storyService.getStoryById(id);
    return ResponseEntity.ok(story);
  }

  @GetMapping("/storyAuthor/{id}")
  public ResponseEntity<StoryWithAuthorDTO> getStoryWuthAuthor(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id){
     String email = jwt.getClaimAsString("email");
     return ResponseEntity.ok(storyService.getStoryWithAuthor(email, id));
  }
  @PutMapping("/update/{id}")
  public ResponseEntity<StoryResponseDTO> updateStory(@PathVariable Long id,
      @RequestBody StoryRequestDTO storyRequestDTO, @AuthenticationPrincipal Jwt jwt) {
    String email = jwt.getClaimAsString("email");
    StoryResponseDTO updated = storyService.updateStory(id, storyRequestDTO, email);
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteStory(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
    String email = jwt.getClaimAsString("email");
      storyService.deleteStory(id, email);

   return ResponseEntity.noContent().build();
  }
}
