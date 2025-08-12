package com.apirest.apirest.Dtos;

import java.time.LocalDateTime;

import com.apirest.apirest.Entities.Chapter;

public class ChapterResponseDTO {

   private Long idChapter;
   private String title;
   private LocalDateTime createdAt;

   public ChapterResponseDTO(Chapter chapter) {
      this.idChapter = chapter.getIdChapter();
      this.title = chapter.getTitle();
      this.createdAt = chapter.getCreatedAt();
   }

   public ChapterResponseDTO(Long idChapter, String title, LocalDateTime createdAt) {
      this.idChapter = idChapter;
      this.title = title;
      this.createdAt = createdAt;
   }

   public Long getIdChapter() {
      return idChapter;
   }

   public void setIdChapter(Long idChapter) {
      this.idChapter = idChapter;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

}
