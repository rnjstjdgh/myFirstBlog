package com.example.demo.DailyLife;

import com.example.demo.Utils.TimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SequenceGenerator(name = "DailyLife_SEQ_GENERATOR", sequenceName = "DailyLife_SEQ", initialValue = 1, allocationSize = 1)
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "DailyLifeContent")
public class DailyLifeContentEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE , generator="DailyLife_SEQ_GENERATOR")
    private Long id;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Builder
    public DailyLifeContentEntity(Long id, String title, String content, String writer){
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

}
