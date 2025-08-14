package com.apirest.apirest.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apirest.apirest.Dtos.PageRequestDTO;
import com.apirest.apirest.Dtos.PageResponseDTO;
import com.apirest.apirest.Entities.Chapter;
import com.apirest.apirest.Entities.Page;
import com.apirest.apirest.Entities.User;
import com.apirest.apirest.Repositories.ChapterRepository;
import com.apirest.apirest.Repositories.PageRepository;
import com.apirest.apirest.Repositories.StoryRepository;
import com.apirest.apirest.Repositories.UserRepository;

@Service
public class PageService {

   @Autowired
   private PageRepository pageRepository;
   @Autowired
   private ChapterRepository chapterRepository;

   @Autowired
   private UserRepository userRepository;
   @Autowired
   private StoryRepository storyRepository;

   public PageResponseDTO createPage(Long storyId, Long chapterId, PageRequestDTO dto, String email) {
      User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

      Chapter chapter = chapterRepository.findById(chapterId)
            .orElseThrow(() -> new RuntimeException("Chapter not found"));

      // Validar que el capítulo pertenece a la historia indicada
      if (!chapter.getStory().getId().equals(storyId)) {
         throw new RuntimeException("Chapter does not belong to the provided story");
      }

      // Validar que el usuario es el autor
      if (!chapter.getStory().getAuthor().getId().equals(user.getId())) {
         throw new RuntimeException("Access denied");
      }

      Page page = new Page();
      page.setContent(dto.getContent());
      System.err.println("Page number recibido: " + dto.getPageNumber());
      page.setPageNumber(dto.getPageNumber());
      System.err.println("Page number en entity: " + page.getPageNumber());
      page.setChapter(chapter);
      page.setCreatedAt(LocalDateTime.now());

      Page savedPage = pageRepository.save(page);
      return new PageResponseDTO(savedPage);
   }
}
