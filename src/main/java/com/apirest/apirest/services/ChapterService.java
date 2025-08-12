package com.apirest.apirest.services;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apirest.apirest.Dtos.ChapterRequestDTO;
import com.apirest.apirest.Dtos.ChapterResponseDTO;
import com.apirest.apirest.Entities.Chapter;
import com.apirest.apirest.Entities.Story;
import com.apirest.apirest.Entities.User;
import com.apirest.apirest.Repositories.ChapterRepository;
import com.apirest.apirest.Repositories.StoryRepository;
import com.apirest.apirest.Repositories.UserRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
public class ChapterService {

   @Autowired
   private ChapterRepository chapterRepository;
   @Autowired
   private StoryRepository storyRepository;

   @Autowired
   private UserRepository userRepository;

   public ChapterResponseDTO createChapter(@RequestBody ChapterRequestDTO dto, String email, Long story_id) {

      User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

      Story story = storyRepository.findStoryById(story_id).orElseThrow(() -> new RuntimeException("Story not found"));

      if (!story.getAuthor().getId().equals(user.getId())) {
         throw new RuntimeException("Access denied");
      }

      Chapter chapter = new Chapter();
      chapter.setTitle(dto.getTitle());
      chapter.setStory(story);
      chapter.setCreatedAt(LocalDateTime.now());

      Chapter savedChapter = chapterRepository.save(chapter);
      return new ChapterResponseDTO(savedChapter);
   }
}
