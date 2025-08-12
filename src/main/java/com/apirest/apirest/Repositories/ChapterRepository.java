package com.apirest.apirest.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirest.apirest.Entities.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

   List<Chapter> findByStoryId(Long storyId);

   Optional<Chapter> findById(Long id);
}
