package com.apirest.apirest.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String nameUser;

   @Column(nullable = false, unique = true)
   private String email;

   @Column(nullable = true)
   private String profile_picture_url;

   public User(Long id, String nameUser, String email, String profile_picture_url) {
      this.id = id;
      this.nameUser = nameUser;
      this.email = email;
      this.profile_picture_url = profile_picture_url;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNameUser() {
      return nameUser;
   }

   public void setNameUser(String nameUser) {
      this.nameUser = nameUser;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getProfile_picture_url() {
      return profile_picture_url;
   }

   public void setProfile_picture_url(String profile_picture_url) {
      this.profile_picture_url = profile_picture_url;
   }
}
