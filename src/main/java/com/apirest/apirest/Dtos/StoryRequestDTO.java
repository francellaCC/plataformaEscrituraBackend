package com.apirest.apirest.Dtos;

public class StoryRequestDTO {

   private String title;
   private String description;
   private String coverImageUrl;
   private String visibility;
   private String status;

   public StoryRequestDTO() {
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
