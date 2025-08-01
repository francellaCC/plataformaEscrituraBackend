package com.apirest.apirest.Entities;
import jakarta.persistence.*;


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
   private String picture;

  @Column(nullable = true)
  private String nickname;

   public User() {
    
   }

   public User(Long id, String nameUser, String email, String picture, String nickname) {
      this.id = id;
      this.nameUser = nameUser;
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

   public String getPicture() {
      return picture;
   }

   public void setPicture(String picture) {
      this.picture = picture;
   }

   public void setNickname(String nickname){
     this.nickname = nickname;
   }

   public String getNickname( String nickname){
      return nickname;
   }
}
