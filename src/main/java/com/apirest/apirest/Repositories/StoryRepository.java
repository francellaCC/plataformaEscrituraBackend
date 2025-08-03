package com.apirest.apirest.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.apirest.Entities.Story;

public interface StoryRepository extends JpaRepository<Story, Long> {
   
   List<Story> findByAuthorId(Long author_id);
}
