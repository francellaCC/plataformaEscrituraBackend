package com.apirest.apirest.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apirest.apirest.Dtos.StoryRequestDTO;
import com.apirest.apirest.Dtos.StoryResponseDTO;
import com.apirest.apirest.Dtos.StoryWithAuthorDTO;
import com.apirest.apirest.Entities.Story;
import com.apirest.apirest.Entities.User;
import com.apirest.apirest.Repositories.StoryRepository;
import com.apirest.apirest.Repositories.UserRepository;

@Service
public class StoryService {

   @Autowired
   private StoryRepository storyRepository;

   @Autowired
   private UserRepository userRepository;

   public StoryResponseDTO createStory(StoryRequestDTO dto, String email) {
      User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

      System.err.println(dto.getGenre());
      Story story = new Story();
      story.setTitle(dto.getTitle());
      story.setGenre(dto.getGenre());
      story.setDescription(dto.getDescription());
      story.setCoverImageUrl(dto.getCoverImageUrl());
      story.setVisibility(dto.getVisibility());
      story.setStatus(dto.getStatus());
      story.setCreatedAt(LocalDateTime.now());
      story.setAuthor(user);

      Story saved = storyRepository.save(story);
      return new StoryResponseDTO(saved);
   }

   public List<StoryResponseDTO> getUserStories(String email) {
      User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
      return storyRepository.findByAuthorId(user.getId())
            .stream()
            .map(StoryResponseDTO::new)
            .collect(Collectors.toList());
   }

   public StoryResponseDTO getStoryById(Long id) {
      Story story = storyRepository.findStoryById(id)
            .orElseThrow(() -> new RuntimeException("Story not found with id: " + id));
      return new StoryResponseDTO(story);
   }

   public StoryWithAuthorDTO getStoryWithAuthor(String email, Long storyId) {

      User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
      Story story = storyRepository.findStoryById(storyId)
            .orElseThrow(() -> new RuntimeException("Story not found"));

      if (!story.getAuthor().getId().equals(user.getId())) {
         throw new RuntimeException("This chapter does not belong to the provided story");
      }

      return new StoryWithAuthorDTO(story.getId(), story.getTitle(), story.getCoverImageUrl(), story.getGenre(),
            user.getId(),
            user.getPicture(), user.getNickname(), story.getCreatedAt());
   }

   public StoryResponseDTO updateStory(Long id, StoryRequestDTO storyRequestDTO, String email) {
      Optional<User> existingUser = userRepository.findByEmail(email);

      if (existingUser.isPresent()) {
         Story story = storyRepository.findStoryById(id)
               .orElseThrow(() -> new RuntimeException("Story not found with id: " + id));

         story.setTitle(storyRequestDTO.getTitle());
         story.setDescription(storyRequestDTO.getDescription());
         story.setCoverImageUrl(storyRequestDTO.getCoverImageUrl());
         story.setGenre(storyRequestDTO.getGenre());
         story.setVisibility(storyRequestDTO.getVisibility());
         story.setStatus(storyRequestDTO.getStatus());

         Story updatedStory = storyRepository.save(story);
         return new StoryResponseDTO(updatedStory);
      } else {
         return null;
      }

   }

   public void deleteStory(Long id, String email) {
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Story story = storyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Story not found with id: " + id));

    if (!story.getAuthor().getId().equals(user.getId())) {
        throw new RuntimeException("This story does not belong to the authenticated user");
    }

    storyRepository.delete(story);
}

}
