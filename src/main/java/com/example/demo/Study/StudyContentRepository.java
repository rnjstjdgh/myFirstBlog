package com.example.demo.Study;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyContentRepository extends JpaRepository<StudyContentEntity, Long> {

    List<StudyContentEntity> findByTitleContaining(String keyword);

}
