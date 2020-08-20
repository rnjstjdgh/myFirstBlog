package com.example.demo.DailyLife;

import com.example.demo.Carrer.CarrerContentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyLifeContentRepository extends JpaRepository<DailyLifeContentEntity,Long> {

    List<DailyLifeContentEntity> findByTitleContaining(String keyword);

    Page<DailyLifeContentEntity> findBySubCategory(String subCategory, Pageable createDate);

    List<DailyLifeContentEntity> findBySubCategoryAndTitleContaining(String subCategory, String keyword);


}
