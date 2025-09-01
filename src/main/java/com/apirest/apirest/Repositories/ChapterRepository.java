package com.apirest.apirest.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apirest.apirest.Entities.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

   List<Chapter> findByStoryId(Long storyId);

   Optional<Chapter> findByIdChapter(Long id);

   @Query("""
             SELECT c FROM Chapter c
             LEFT JOIN FETCH c.pages p
             WHERE c.idChapter = :chapterId
             AND c.story.id = :storyId
         """)
   Optional<Chapter> findChapterWithPages(
         @Param("chapterId") Long chapterId,
         @Param("storyId") Long storyId);
}
