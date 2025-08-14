package com.apirest.apirest.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.apirest.apirest.Entities.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {


}
