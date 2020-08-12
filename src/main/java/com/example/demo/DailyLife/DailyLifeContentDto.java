package com.example.demo.DailyLife;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DailyLifeContentDto {

    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public DailyLifeContentEntity toEntity(){
        DailyLifeContentEntity build = DailyLifeContentEntity.builder()
                                            .id(id)
                                            .writer(writer)
                                            .title(title)
                                            .content(content)
                                            .build();
        return build;
    }

    @Builder
    public DailyLifeContentDto(Long id, String title, String content, String writer, LocalDateTime createDate, LocalDateTime modifiedDate){
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

}
