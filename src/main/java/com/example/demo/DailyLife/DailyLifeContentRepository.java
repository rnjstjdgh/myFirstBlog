package com.example.demo.DailyLife;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyLifeContentRepository extends JpaRepository<DailyLifeContentEntity,Long> {

    List<DailyLifeContentEntity> findByTitleContaining(String keyword);
}
