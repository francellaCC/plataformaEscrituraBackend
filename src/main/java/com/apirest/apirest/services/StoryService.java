package com.apirest.apirest.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apirest.apirest.Dtos.StoryRequestDTO;
import com.apirest.apirest.Dtos.StoryResponseDTO;
import com.apirest.apirest.Entities.Story;
import com.apirest.apirest.Entities.User;
import com.apirest.apirest.Repositories.StoryRepository;
import com.apirest.apirest.Repositories.UserRepository;

@Service
public class StoryService {

   @Autowired
   private StoryRepository storyRepository;
   private UserRepository userRepository;

   public StoryResponseDTO createStory(StoryRequestDTO dto, String email) {
      User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

      Story story = new Story();
      story.setTitle(dto.getTitle());
      story.setDescription(dto.getDescription());
      story.setCoverImageUrl(dto.getCoverImageUrl());
      story.setVisibility(dto.getVisibility());
      story.setStatus(dto.getStatus());
      story.setCreatedAt(LocalDateTime.now());
      story.setAuthor(user);

      Story saved = storyRepository.save(story);
      return new StoryResponseDTO(saved);
   }
}
