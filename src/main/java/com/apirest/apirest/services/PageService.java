package com.apirest.apirest.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apirest.apirest.Dtos.PageRequestDTO;
import com.apirest.apirest.Dtos.PageResponseDTO;
import com.apirest.apirest.Dtos.PaginatedPagesDTO;
import com.apirest.apirest.Entities.Chapter;
import com.apirest.apirest.Entities.PageEntity;
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

      PageEntity page = new PageEntity();
      page.setContent(dto.getContent());
      System.err.println("Page number recibido: " + dto.getPageNumber());
      page.setPageNumber(dto.getPageNumber());
      System.err.println("Page number en entity: " + page.getPageNumber());
      page.setChapter(chapter);
      page.setCreatedAt(LocalDateTime.now());

      PageEntity savedPage = pageRepository.save(page);
      return new PageResponseDTO(savedPage);
   }

   public PaginatedPagesDTO getPageByChapterId(Long storyId, Long chapterId, String email, int limit, int offset) {
      User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

      Chapter chapter = chapterRepository.findById(chapterId)
            .orElseThrow(() -> new RuntimeException("Chapter not found"));

      // Validar que el usuario es el autor
      if (!chapter.getStory().getAuthor().getId().equals(user.getId())) {
         throw new RuntimeException("Access denied");
      }

      // Validar que el capítulo pertenece a la historia indicada
      if (!chapter.getStory().getId().equals(storyId)) {
         throw new RuntimeException("Chapter does not belong to the provided story");
      }

      // Paginación con JPA
      Pageable pageable = PageRequest.of(offset / limit, limit, Sort.by("pageNumber").ascending());
      Page<PageEntity> pagesPage = pageRepository.findByChapter_IdChapterOrderByPageNumberAsc(chapterId, pageable);

      // Mapear a DTO
      List<PageResponseDTO> dtoList = pagesPage.stream().map(p -> {
         PageResponseDTO dto = new PageResponseDTO();
         dto.setId(p.getId());
         dto.setContent(p.getContent());
         dto.setPageNumber(p.getPageNumber());
         dto.setChapterId(p.getChapter().getIdChapter());
         dto.setCreatedAt(p.getCreatedAt());
         return dto;
      }).collect(Collectors.toList());

      // Construir respuesta paginada
      PaginatedPagesDTO response = new PaginatedPagesDTO();
      response.setPages(dtoList);
      response.setTotal(pagesPage.getTotalElements()); // total real de páginas

      return response;
   }

   public PageResponseDTO updatePage(Long storyId, Long chapterId, Long pageId, String email, PageRequestDTO dto) {
      User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

      Chapter chapter = chapterRepository.findById(chapterId)
            .orElseThrow(() -> new RuntimeException("Chapter not found"));

      // Validar que el usuario es el autor
      if (!chapter.getStory().getAuthor().getId().equals(user.getId())) {
         throw new RuntimeException("Access denied");
      }

      // Validar que el capítulo pertenece a la historia indicada
      if (!chapter.getStory().getId().equals(storyId)) {
         throw new RuntimeException("Chapter does not belong to the provided story");
      }

      //Validar que la pagina pertenezca al capitulo indicado
      PageEntity pageEntity = pageRepository.findById(pageId) .orElseThrow(() -> new RuntimeException("Page not found"));

      if(!pageEntity.getChapter().getIdChapter().equals(chapter.getIdChapter())){
          throw new RuntimeException("Page does not belong to the provided chapter");
      }

      pageEntity.setContent(dto.getContent());
      pageEntity.setPageNumber(dto.getPageNumber());
      return new PageResponseDTO(pageRepository.save(pageEntity));
   }
}
