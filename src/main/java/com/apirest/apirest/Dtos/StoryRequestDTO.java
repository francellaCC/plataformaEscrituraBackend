package com.apirest.apirest.Dtos;

public class StoryRequestDTO {

   private String title;
   private String description;
   private String genre;
   private String coverImageUrl;
   private String visibility;
   private String status;

   public StoryRequestDTO(String title, String description, String genre, String coverImageUrl, String visibility,
         String status) {
      this.title = title;
      this.description = description;
      this.genre = genre;
      this.coverImageUrl = coverImageUrl;
      this.visibility = visibility;
      this.status = status;
   }



   public StoryRequestDTO() {
   }

   

   public String getGenre() {
      return genre;
   }

   public void setGenre(String genre) {
      this.genre = genre;
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

}
