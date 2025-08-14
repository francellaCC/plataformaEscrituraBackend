package com.apirest.apirest.Dtos;

public class PageRequestDTO {

   private String content;
   private Integer pageNumber;

   public PageRequestDTO() {
   }

   public PageRequestDTO(String content, Integer pageNumber) {
      this.content = content;
      this.pageNumber = pageNumber;
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

}
