package com.example.demo.Study;

import com.example.demo.Carrer.CarrerContentEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudyContentDto {

    private Long id;
    private String writer;
    private String title;
    private String subCategory;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public StudyContentEntity toEntity(){
        StudyContentEntity build = StudyContentEntity.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .subCategory(subCategory)
                .content(content)
                .build();
        return build;
    }

    @Builder
    public StudyContentDto(Long id, String title, String content, String writer,String subCategory
                            , LocalDateTime createDate, LocalDateTime modifiedDate){
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.subCategory = subCategory;
        this.content = content;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }
}
