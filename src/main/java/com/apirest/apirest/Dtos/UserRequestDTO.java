package com.apirest.apirest.Dtos;

public class UserRequestDTO {

   private Long id;

   private String name;
   private String picture;

   public UserRequestDTO(Long id, String name, String picture) {
      this.name = name;
      this.picture = picture;
   }

   public UserRequestDTO() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPicture() {
      return picture;
   }

   public void setPicture(String picture) {
      this.picture = picture;
   }

}
