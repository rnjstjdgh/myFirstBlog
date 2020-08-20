package com.example.demo.Study;

import com.example.demo.Carrer.CarrerContentDto;
import com.example.demo.Carrer.CarrerContentEntity;
import com.example.demo.Carrer.CarrerContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudyContentService {

    @Autowired
    private StudyContentRepository studyContentRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 5;  //블럭에 존재하는 페이지 수
    private static final int PAGE_POST_COUNT = 4;       //한 페이지에 존재하는 게시글 수

    @Transactional
    public Long SaveStudyContent(StudyContentDto studyContentDto){
        return studyContentRepository.save(studyContentDto.toEntity()).getId();
    }

    @Transactional
    public List<StudyContentDto> GetStudyContentList(Integer pageNum,String subCategory){

        Page<StudyContentEntity> page;
        if(subCategory == "total")
            page = studyContentRepository.
                    findAll(
                            PageRequest.of(pageNum-1,PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createDate")));
        else
            page = studyContentRepository.
                    findBySubCategory(subCategory,
                            PageRequest.of(pageNum-1,PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createDate")));

        List<StudyContentEntity> studyContentEntities = page.getContent();
        List<StudyContentDto> studyContentDtoList = new ArrayList<>();

        for(StudyContentEntity studyContentEntity : studyContentEntities){

            studyContentDtoList.add(this.convertEntityToDto(studyContentEntity));

        }
        return studyContentDtoList;
    }

    @Transactional
    public StudyContentDto GetContent(Long id){
        Optional<StudyContentEntity> studyContentEntityWrapper = studyContentRepository.findById(id);
        StudyContentEntity studyContentEntity = studyContentEntityWrapper.get();

        StudyContentDto studyContentDto = StudyContentDto.builder()
                .id(studyContentEntity.getId())
                .title(studyContentEntity.getTitle())
                .content(studyContentEntity.getContent())
                .writer(studyContentEntity.getWriter())
                .createDate(studyContentEntity.getCreateDate())
                .build();
        return studyContentDto;
    }

    @Transactional
    public void DeleteStudyContent(Long id){
        studyContentRepository.deleteById(id);
    }

    @Transactional
    public List<StudyContentDto> SearchStudyContents(String keyword, String subCategory){
        List<StudyContentEntity> studyContentEntities;
        if (subCategory == "total")
            studyContentEntities = studyContentRepository.findByTitleContaining(keyword);
        else
            studyContentEntities = studyContentRepository.findBySubCategoryAndTitleContaining(subCategory, keyword);

        List<StudyContentDto> studyContentDtoList = new ArrayList<>();

        if(studyContentEntities.isEmpty()) return studyContentDtoList;

        for(StudyContentEntity studyContentEntity : studyContentEntities){
            studyContentDtoList.add(this.convertEntityToDto(studyContentEntity));
        }

        return studyContentDtoList;
    }

    private StudyContentDto convertEntityToDto(StudyContentEntity studyContentEntity){
        return StudyContentDto.builder()
                .id(studyContentEntity.getId())
                .title(studyContentEntity.getTitle())
                .content(studyContentEntity.getContent())
                .writer(studyContentEntity.getWriter())
                .subCategory(studyContentEntity.getSubCategory())
                .createDate(studyContentEntity.getCreateDate())
                .build();
    }

    public Integer[] GetPageList(Integer currentPageNum){
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        //총 게시글 수
        Double postTotalCount = Double.valueOf(this.GetStudyContentCount());

        //총 게시글 수를 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postTotalCount/PAGE_POST_COUNT)));

        //현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum+1 > currentPageNum + BLOCK_PAGE_NUM_COUNT)
                ? currentPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum+1;

        System.out.println(currentPageNum);
        System.out.println(blockLastPageNum);

        //페이지 시작 번호 조정
        currentPageNum = (currentPageNum <=3)? 1:currentPageNum-2;

        //페이지 번호 할당
        for(int val = currentPageNum, i = 0; val<blockLastPageNum && i<5;val++,i++){
            pageList[i] = val;
        }
        return pageList;
    }


    @Transactional
    public Long GetStudyContentCount() {
        return studyContentRepository.count();
    }
}
