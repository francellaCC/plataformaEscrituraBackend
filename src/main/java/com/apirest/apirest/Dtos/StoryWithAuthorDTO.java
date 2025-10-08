package com.apirest.apirest.Dtos;

import java.time.LocalDateTime;

public class StoryWithAuthorDTO {
   private Long id;
   private String title;
   private String coverImageUrl;
   private String genre;
   private LocalDateTime createdAt;
   private Long authorId;
   private String picture;
   private String nickname;

   public StoryWithAuthorDTO() {
   }

   public StoryWithAuthorDTO(Long id, String title, String coverImageUrl, String genre, Long authorId, String picture,
         String nickname, LocalDateTime createdAt) {
      this.id = id;
      this.title = title;
      this.coverImageUrl = coverImageUrl;
      this.genre = genre;
      this.authorId = authorId;
      this.picture = picture;
      this.nickname = nickname;
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

   public String getCoverImageUrl() {
      return coverImageUrl;
   }

   public void setCoverImageUrl(String coverImageUrl) {
      this.coverImageUrl = coverImageUrl;
   }

   public String getGenre() {
      return genre;
   }

   public void setGenre(String genre) {
      this.genre = genre;
   }

   public Long getAuthorId() {
      return authorId;
   }

   public void setAuthorId(Long authorId) {
      this.authorId = authorId;
   }

   public String getPicture() {
      return picture;
   }

   public void setPicture(String picture) {
      this.picture = picture;
   }

   public String getNickname() {
      return nickname;
   }

   public void setNickname(String nickname) {
      this.nickname = nickname;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

}
