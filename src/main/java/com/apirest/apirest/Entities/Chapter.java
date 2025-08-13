package com.apirest.apirest.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Chapter {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idChapter;

   @Column(nullable = false)
   private String title;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "story_id")
   private Story story;

   @Column(name = "created_at")
   private LocalDateTime createdAt;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Page> pages = new ArrayList<>();


   public Chapter() {
   }


   public Chapter(Long idChapter, String title, Story story, LocalDateTime createdAt) {
      this.idChapter = idChapter;
      this.title = title;
      this.story = story;
      this.createdAt = createdAt;
   }

   
   public Long getIdChapter() {
      return idChapter;
   }

   public void setIdChapter(Long idChapter) {
      this.idChapter = idChapter;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public Story getStory() {
      return story;
   }

   public void setStory(Story story) {
      this.story = story;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

    public List<Page> getPages() {
      return pages;
   }


    public void setPages(List<Page> pages) {
       this.pages = pages;
    }
}
