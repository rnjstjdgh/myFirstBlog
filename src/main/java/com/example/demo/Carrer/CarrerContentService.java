package com.example.demo.Carrer;

import com.example.demo.DailyLife.DailyLifeContentDto;
import com.example.demo.DailyLife.DailyLifeContentEntity;
import com.example.demo.DailyLife.DailyLifeContentRepository;
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
public class CarrerContentService {

    @Autowired
    private CarrerContentRepository carrerContentRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 5;  //블럭에 존재하는 페이지 수
    private static final int PAGE_POST_COUNT = 4;       //한 페이지에 존재하는 게시글 수

    @Transactional
    public Long SaveCarrerContent(CarrerContentDto carrerContentDto){
        return carrerContentRepository.save(carrerContentDto.toEntity()).getId();
    }

    @Transactional
    public List<CarrerContentDto> GetCarrerContentList(Integer pageNum, String subCategory){
        Page<CarrerContentEntity> page;
        if(subCategory == "total")
            page = carrerContentRepository.
                    findAll(
                            PageRequest.of(pageNum-1,PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createDate")));
        else
            page = carrerContentRepository.
                    findBySubCategory(subCategory,
                            PageRequest.of(pageNum-1,PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createDate")));

        List<CarrerContentEntity> carrerContentEntities = page.getContent();
        List<CarrerContentDto> carrerContentDtoList = new ArrayList<>();
        for(CarrerContentEntity carrerContentEntity : carrerContentEntities){

            carrerContentDtoList.add(this.convertEntityToDto(carrerContentEntity));

        }
        return carrerContentDtoList;
    }



    @Transactional
    public CarrerContentDto GetContent(Long id){
        Optional<CarrerContentEntity> carrerContentEntityWrapper = carrerContentRepository.findById(id);
        CarrerContentEntity carrerContentEntity = carrerContentEntityWrapper.get();

        CarrerContentDto carrerContentDto = CarrerContentDto.builder()
                .id(carrerContentEntity.getId())
                .title(carrerContentEntity.getTitle())
                .content(carrerContentEntity.getContent())
                .writer(carrerContentEntity.getWriter())
                .createDate(carrerContentEntity.getCreateDate())
                .build();
        return carrerContentDto;
    }

    @Transactional
    public void DeleteCarrerContent(Long id){
        carrerContentRepository.deleteById(id);
    }

    @Transactional
    public List<CarrerContentDto> SearchCarrerContents(String keyword, String subCategory){
        List<CarrerContentEntity> carrerContentEntities;

        if(subCategory == "total")
            carrerContentEntities = carrerContentRepository.findByTitleContaining(keyword);
        else
            carrerContentEntities = carrerContentRepository.findBySubCategoryAndTitleContaining(subCategory,keyword);

        List<CarrerContentDto> carrerContentDtoList = new ArrayList<>();

        if(carrerContentEntities.isEmpty()) return carrerContentDtoList;

        for(CarrerContentEntity carrerContentEntity : carrerContentEntities){
            carrerContentDtoList.add(this.convertEntityToDto(carrerContentEntity));
        }

        return carrerContentDtoList;
    }

    private CarrerContentDto convertEntityToDto(CarrerContentEntity carrerContentEntity){
        return CarrerContentDto.builder()
                .id(carrerContentEntity.getId())
                .title(carrerContentEntity.getTitle())
                .content(carrerContentEntity.getContent())
                .writer(carrerContentEntity.getWriter())
                .subCategory(carrerContentEntity.getSubCategory())
                .createDate(carrerContentEntity.getCreateDate())
                .build();
    }

    public Integer[] GetPageList(Integer currentPageNum, String subCategory){
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        //총 게시글 수
        Double postTotalCount = Double.valueOf(this.GetCarrerContentCount(subCategory));

        //총 게시글 수를 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postTotalCount/PAGE_POST_COUNT)));

        //현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum+1 > currentPageNum + BLOCK_PAGE_NUM_COUNT)
                ? currentPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum+1;

        //페이지 시작 번호 조정
        currentPageNum = (currentPageNum <=3)? 1:currentPageNum-2;

        //페이지 번호 할당
        for(int val = currentPageNum, i = 0; val<blockLastPageNum && i<5;val++,i++){
            pageList[i] = val;
        }
        return pageList;
    }


    @Transactional
    public Long GetCarrerContentCount(String subCategory) {
        if(subCategory == "total")
            return carrerContentRepository.count();
        else
            return new Long(carrerContentRepository.findBySubCategory(subCategory).size());
    }

    @Transactional
    public Long titleOverlapCheck(String title){
        if(carrerContentRepository.findByTitle(title).size() != 0)
            return new Long(1);
        else
            return new Long(-1);
    }
}
