package com.apirest.apirest.Dtos;

import com.apirest.apirest.Entities.User;

public class UserResponseDTO {
 
   private Long id;
   private String name;
   private String email;
   private String picture;
   private String nickname;

   
   public UserResponseDTO(User user){
      this.id = user.getId();
      this.email= user.getEmail();
      this.name = user.getNameUser();
      this.picture = user.getPicture();
      this.nickname = user.getNickname();
   }
     public UserResponseDTO(Long id, String name, String email, String picture, String nickname) {
      this.id = id;
      this.name = name;
      this.email = email;
      this.picture = picture;
      this.nickname = nickname;
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

     public String getEmail() {
        return email;
     }

     public void setEmail(String email) {
        this.email = email;
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
}
