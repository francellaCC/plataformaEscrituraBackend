package com.apirest.apirest.Dtos;

import java.time.LocalDateTime;

import com.apirest.apirest.Entities.Story;

public class StoryResponseDTO {
   private Long id;
   private String title;
   private String description;
   private String coverImageUrl;
   private String visibility;
   private String status;
   private LocalDateTime createdAt;

   public StoryResponseDTO(Story story) {
      this.id = story.getId();
      this.title = story.getTitle();
      this.description = story.getDescription();
      this.coverImageUrl = story.getCoverImageUrl();
      this.visibility = story.getVisibility();
      this.status = story.getStatus();
      this.createdAt = story.getCreatedAt();
   }

   public StoryResponseDTO(Long id, String title, String description, String coverImageUrl, String visibility,
         String status, LocalDateTime createdAt) {
      this.id = id;
      this.title = title;
      this.description = description;
      this.coverImageUrl = coverImageUrl;
      this.visibility = visibility;
      this.status = status;
      this.createdAt = createdAt;
   }

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

}
