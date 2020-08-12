package com.example.demo.Carrer;

import com.example.demo.DailyLife.DailyLifeContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrerContentRepository extends JpaRepository<CarrerContentEntity,Long> {
    List<CarrerContentEntity> findByTitleContaining(String keyword);

}
