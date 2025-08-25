package com.apirest.apirest.Dtos;

import java.util.List;

public class ChapterWithPagesDTO {
   private Long idChapter;
   private String title;
   private List<PageResponseDTO> pages;

   public ChapterWithPagesDTO(Long idChapter, String title, List<PageResponseDTO> pages) {
      this.idChapter = idChapter;
      this.title = title;
      this.pages = pages;
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

   public List<PageResponseDTO> getPages() {
      return pages;
   }

   public void setPages(List<PageResponseDTO> pages) {
      this.pages = pages;
   }
}
