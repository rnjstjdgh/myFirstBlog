package com.example.demo.DailyLife;

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
public class DailyLifeContentService {

    @Autowired
    private DailyLifeContentRepository dailyLifeContentRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 5;  //블럭에 존재하는 페이지 수
    private static final int PAGE_POST_COUNT = 4;       //한 페이지에 존재하는 게시글 수

    @Transactional
    public Long SaveDailyLifeContent(DailyLifeContentDto dailyLifeContentDto){
        return dailyLifeContentRepository.save(dailyLifeContentDto.toEntity()).getId();
    }

    @Transactional
    public List<DailyLifeContentDto> GetDailyLifeContentList(Integer pageNum,String subCategory){

        Page<DailyLifeContentEntity> page;
        if(subCategory == "total")
            page = dailyLifeContentRepository
                    .findAll(
                            PageRequest.of(pageNum-1,PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createDate")));
        else
            page = dailyLifeContentRepository
                    .findBySubCategory(subCategory,
                            PageRequest.of(pageNum-1,PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createDate")));


        List<DailyLifeContentEntity> dailyLifeContentEntities = page.getContent();
        List<DailyLifeContentDto> dailyLifeContentDtoList = new ArrayList<>();

        for(DailyLifeContentEntity dailyLifeContentEntity : dailyLifeContentEntities){

            dailyLifeContentDtoList.add(this.convertEntityToDto(dailyLifeContentEntity));

//            DailyLifeContentDto dailyLifeContentDto = DailyLifeContentDto.builder()
//                    .id(dailyLifeContentEntity.getId())
//                    .title(dailyLifeContentEntity.getTitle())
//                    .content(dailyLifeContentEntity.getContent())
//                    .writer(dailyLifeContentEntity.getWriter())
//                    .createDate(dailyLifeContentEntity.getCreateDate())
//                    .build();
//            dailyLifeContentDtoList.add(dailyLifeContentDto);
        }
        return dailyLifeContentDtoList;
    }

    @Transactional
    public DailyLifeContentDto GetContent(Long id){
        Optional<DailyLifeContentEntity> dailyLifeContentEntityWrapper = dailyLifeContentRepository.findById(id);
        DailyLifeContentEntity dailyLifeContentEntity = dailyLifeContentEntityWrapper.get();

        DailyLifeContentDto dailyLifeContentDto = DailyLifeContentDto.builder()
                .id(dailyLifeContentEntity.getId())
                .title(dailyLifeContentEntity.getTitle())
                .content(dailyLifeContentEntity.getContent())
                .writer(dailyLifeContentEntity.getWriter())
                .createDate(dailyLifeContentEntity.getCreateDate())
                .build();
        return dailyLifeContentDto;
    }

    @Transactional
    public void DeleteDailyLifeContent(Long id){
        dailyLifeContentRepository.deleteById(id);
    }

    @Transactional
    public List<DailyLifeContentDto> SearchDailyLifeContents(String keyword , String subCategory){
        List<DailyLifeContentEntity> dailyLifeContentEntities;
        if(subCategory == "total")
            dailyLifeContentEntities = dailyLifeContentRepository.findByTitleContaining(keyword);
        else
            dailyLifeContentEntities = dailyLifeContentRepository.findBySubCategoryAndTitleContaining(subCategory, keyword);


        List<DailyLifeContentDto> dailyLifeContentDtoList = new ArrayList<>();

        if(dailyLifeContentEntities.isEmpty()) return dailyLifeContentDtoList;

        for(DailyLifeContentEntity dailyLifeContentEntity : dailyLifeContentEntities){
            dailyLifeContentDtoList.add(this.convertEntityToDto(dailyLifeContentEntity));
        }

        return dailyLifeContentDtoList;
    }

    private DailyLifeContentDto convertEntityToDto(DailyLifeContentEntity dailyLifeContentEntity){
        return DailyLifeContentDto.builder()
                .id(dailyLifeContentEntity.getId())
                .title(dailyLifeContentEntity.getTitle())
                .content(dailyLifeContentEntity.getContent())
                .writer(dailyLifeContentEntity.getWriter())
                .subCategory(dailyLifeContentEntity.getSubCategory())
                .createDate(dailyLifeContentEntity.getCreateDate())
                .build();
    }

    public Integer[] GetPageList(Integer currentPageNum, String subCategory){
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        //총 게시글 수
        Double postTotalCount = Double.valueOf(this.GetDailyLifeContentCount(subCategory));

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
    public Long GetDailyLifeContentCount(String subCategory) {
        if(subCategory == "total")
            return dailyLifeContentRepository.count();
        else
            return new Long(dailyLifeContentRepository.findBySubCategory(subCategory).size());
    }

}
