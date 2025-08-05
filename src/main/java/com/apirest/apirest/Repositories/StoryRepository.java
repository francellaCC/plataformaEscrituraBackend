package com.apirest.apirest.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirest.apirest.Entities.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
   List<Story> findByAuthorId(Long author_id);

   Optional<Story> findStoryById(Long id);
}
