package com.apirest.apirest.Dtos;

import java.util.List;

public class PaginatedPagesDTO {

   private List<PageResponseDTO> pages;
   private long total;
   
   
   public PaginatedPagesDTO(List<PageResponseDTO> pages, long total) {
      this.pages = pages;
      this.total = total;
   }
   public PaginatedPagesDTO() {
   }
   public List<PageResponseDTO> getPages() {
      return pages;
   }
   public void setPages(List<PageResponseDTO> pages) {
      this.pages = pages;
   }
   public long getTotal() {
      return total;
   }
   public void setTotal(long total) {
      this.total = total;
   }
}