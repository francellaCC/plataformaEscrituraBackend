package com.apirest.apirest.Dtos;

public class ChapterRequestDTO {

   private Long idChapter;
   private String title;

   public ChapterRequestDTO(Long idChapter, String title) {
      this.idChapter = idChapter;
      this.title = title;
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

}
