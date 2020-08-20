package com.example.demo.Carrer;

import com.example.demo.Utils.TimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SequenceGenerator(name = "Carrer_SEQ_GENERATOR", sequenceName = "Carrer_SEQ", initialValue = 1, allocationSize = 1)
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CarrerContent")
public class CarrerContentEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE , generator="Carrer_SEQ_GENERATOR")
    private Long id;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 10, nullable = false)
    private String subCategory;

    @Lob
    @Column(nullable = false)
    private String content;

    @Builder
    public CarrerContentEntity(Long id, String title, String content, String writer, String subCategory){
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.subCategory = subCategory;
    }
}
