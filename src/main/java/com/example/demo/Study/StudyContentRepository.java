package com.example.demo.Study;

import com.example.demo.Carrer.CarrerContentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyContentRepository extends JpaRepository<StudyContentEntity, Long> {

    List<StudyContentEntity> findByTitleContaining(String keyword);

    Page<StudyContentEntity> findBySubCategory(String subCategory, Pageable createDate);

    List<StudyContentEntity> findBySubCategoryAndTitleContaining(String subCategory, String keyword);

    List<StudyContentEntity> findBySubCategory(String subCategory);
}
