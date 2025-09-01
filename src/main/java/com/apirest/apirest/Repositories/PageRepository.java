package com.apirest.apirest.Repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.apirest.apirest.Entities.PageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface PageRepository extends JpaRepository< PageEntity, Long> {
   Page<PageEntity> findByChapter_IdChapterOrderByPageNumberAsc(Long chapterId, Pageable pageable);

}
