package com.apirest.apirest.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.apirest.apirest.Dtos.ChapterRequestDTO;
import com.apirest.apirest.Dtos.ChapterResponseDTO;
import com.apirest.apirest.Dtos.ChapterWithPagesDTO;
import com.apirest.apirest.Dtos.PageResponseDTO;
import com.apirest.apirest.Dtos.StoryResponseDTO;
import com.apirest.apirest.Entities.Chapter;
import com.apirest.apirest.Entities.Story;
import com.apirest.apirest.Entities.User;
import com.apirest.apirest.Repositories.ChapterRepository;
import com.apirest.apirest.Repositories.StoryRepository;
import com.apirest.apirest.Repositories.UserRepository;

@Service
public class ChapterService {

   @Autowired
   private ChapterRepository chapterRepository;
   @Autowired
   private StoryRepository storyRepository;

   @Autowired
   private UserRepository userRepository;

   public ChapterResponseDTO createChapter(ChapterRequestDTO dto, String email, Long storyId) {

      Story story = validateStoryOwnership(storyId, email);

      Chapter chapter = new Chapter();
      chapter.setTitle(dto.getTitle());
      chapter.setStory(story);
      chapter.setCreatedAt(LocalDateTime.now());

      return new ChapterResponseDTO(chapterRepository.save(chapter));
   }

   public List<ChapterResponseDTO> getAllChapter(String email, Long storyId) {

      Story story = validateStoryOwnership(storyId, email);
      return chapterRepository.findByStoryId(story.getId())
            .stream()
            .map(ChapterResponseDTO::new)
            .collect(Collectors.toList());

   }

public ChapterResponseDTO getChapterById(String email, Long storyId, Long chapterId) {
    // Validar que la historia es del usuario
    Story story = validateStoryOwnership(storyId, email);

  Chapter chapter = chapterRepository.findByIdChapter(chapterId).orElseThrow(()
    -> new RuntimeException("Chapter not found or doesn't belong to the story"));

     // Aseguramos que el capítulo pertenece a la historia indicada
      if (!chapter.getStory().getId().equals(story.getId())) {
         throw new RuntimeException("This chapter does not belong to the provided story");
      }

    return new ChapterResponseDTO(chapter);
}


   public ChapterResponseDTO updateChapter(Long storyId, Long chapterId, ChapterRequestDTO dto, String email) {

      Story story = validateStoryOwnership(storyId, email);

      Chapter chapter = chapterRepository.findByIdChapter(chapterId)
            .orElseThrow(() -> new RuntimeException("Chapter not found"));

      // Aseguramos que el capítulo pertenece a la historia indicada
      if (!chapter.getStory().getId().equals(story.getId())) {
         throw new RuntimeException("This chapter does not belong to the provided story");
      }

      chapter.setTitle(dto.getTitle());

      return new ChapterResponseDTO(chapterRepository.save(chapter));

   }

   public void deleteStory(Long storyId, Long chapterId, String email) {

      Story story = validateStoryOwnership(storyId, email);

      Chapter chapter = chapterRepository.findByIdChapter(chapterId)
            .orElseThrow(() -> new RuntimeException("Chapter not found"));

      // Aseguramos que el capítulo pertenece a la historia indicada
      if (!chapter.getStory().getId().equals(story.getId())) {
         throw new RuntimeException("This chapter does not belong to the provided story");
      }

      chapterRepository.delete(chapter);
   }

   private Story validateStoryOwnership(Long storyId, String email) {
      User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

      Story story = storyRepository.findStoryById(storyId)
            .orElseThrow(() -> new RuntimeException("Story not found"));

      if (!story.getAuthor().getId().equals(user.getId())) {
         throw new RuntimeException("Access denied");
      }

      return story;
   }
}
