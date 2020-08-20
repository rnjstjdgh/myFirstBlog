package com.example.demo.Carrer;

import com.example.demo.DailyLife.DailyLifeContentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrerContentRepository extends JpaRepository<CarrerContentEntity,Long> {
    List<CarrerContentEntity> findByTitleContaining(String keyword);

    List<CarrerContentEntity> findBySubCategoryAndTitleContaining(String subCategory, String keyword);


    Page<CarrerContentEntity> findBySubCategory(String subCategory, Pageable createDate);
}
