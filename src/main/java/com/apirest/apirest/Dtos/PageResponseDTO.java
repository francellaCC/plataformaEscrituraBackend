package com.apirest.apirest.Dtos;

import java.time.LocalDateTime;

import com.apirest.apirest.Entities.Page;

public class PageResponseDTO {
   private Long id;
   private String content;
   private Integer pageNumber;
   private Long chapterId;
   private LocalDateTime createdAt;


   public PageResponseDTO(Long id,  Integer pageNumber, String content) {
      this.id = id;
      this.content = content;
      this.pageNumber = pageNumber;
   }
   public PageResponseDTO (Page page){
       this.id = page.getId();
      this.content =page.getContent();
      this.pageNumber = page.getPageNumber();
      this.chapterId = page.getChapter().getIdChapter();
      this.createdAt = page.getCreatedAt();
   }
   public PageResponseDTO(Long id, String content, Integer pageNumber, Long chapterId, LocalDateTime createdAt) {
      this.id = id;
      this.content = content;
      this.pageNumber = pageNumber;
      this.chapterId = chapterId;
      this.createdAt = createdAt;
   }
   
   
   public Long getId() {
      return id;
   }
   public void setId(Long id) {
      this.id = id;
   }
   public String getContent() {
      return content;
   }
   public void setContent(String content) {
      this.content = content;
   }
   public Integer getPageNumber() {
      return pageNumber;
   }
   public void setPageNumber(Integer pageNumber) {
      this.pageNumber = pageNumber;
   }
   public Long getChapterId() {
      return chapterId;
   }
   public void setChapterId(Long chapterId) {
      this.chapterId = chapterId;
   }
   public LocalDateTime getCreatedAt() {
      return createdAt;
   }
   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

   
}
