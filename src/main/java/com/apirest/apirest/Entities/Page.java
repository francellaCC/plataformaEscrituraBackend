package com.apirest.apirest.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pages")
public class Page {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private Integer pageNumber;


   // para que se guarde como tipo TEXT/LONGTEXT en la BD
   @Lob
   @Column(columnDefinition = "TEXT")
   private String content;

   @ManyToOne
   @JoinColumn(name = "chapter_id")
   private Chapter chapter;

   @Column(name = "created_at")
   private LocalDateTime createdAt;

   
   public Page() {
   }

   public Page(Long id, Integer pageNumber, String content, Chapter chapter, LocalDateTime createdAt) {
      this.id = id;
      this.pageNumber = pageNumber;
      this.content = content;
      this.chapter = chapter;
      this.createdAt = createdAt;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Integer getPageNumber() {
      return pageNumber;
   }

   public void setPageNumber(Integer pageNumber) {
      this.pageNumber = pageNumber;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public Chapter getChapter() {
      return chapter;
   }

   public void setChapter(Chapter chapter) {
      this.chapter = chapter;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }
}
