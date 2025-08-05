package com.apirest.apirest.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "stories")
public class Story {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String title;

   @Column(nullable = true)
   private String description;

   @Column(nullable = true)
   private String genre;

   @Column(nullable = true)
   private String coverImageUrl;

   @Column(nullable = true)
   private String visibility;

   @Column(nullable = true)
   private String status;

   @Column(name = "created_at")
   private LocalDateTime createdAt;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "author_id", nullable = false)
   private User author;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getGenre() {
      return genre;
   }

   public void setGenre(String genre) {
      this.genre = genre;
   }

   public String getCoverImageUrl() {
      return coverImageUrl;
   }

   public void setCoverImageUrl(String coverImageUrl) {
      this.coverImageUrl = coverImageUrl;
   }

   public String getVisibility() {
      return visibility;
   }

   public void setVisibility(String visibility) {
      this.visibility = visibility;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

   public User getAuthor() {
      return author;
   }

   public void setAuthor(User author) {
      this.author = author;
   }

   
}
