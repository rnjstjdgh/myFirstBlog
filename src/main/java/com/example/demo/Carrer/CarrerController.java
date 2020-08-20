package com.example.demo.Carrer;

import com.example.demo.Category.CategoryDto;
import com.example.demo.Category.CategoryService;
import com.example.demo.DailyLife.DailyLifeContentDto;
import com.example.demo.DailyLife.DailyLifeContentService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CarrerController {

    @Autowired
    private CarrerContentService carrerContentService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/Carrer/CarrerBoard")
    public String ShowCarrerBoard(Model model, @RequestParam(value = "Page",defaultValue = "1") Integer pageNum, HttpServletRequest request){
        String subCategory = request.getParameter("subCategory");

        List<CarrerContentDto> carrerContentDtoList;
        Integer[] pageList;
        if(subCategory == null){
            carrerContentDtoList = carrerContentService.GetCarrerContentList(pageNum, "total");
            pageList = carrerContentService.GetPageList(pageNum);
        }
        else{
            carrerContentDtoList = carrerContentService.GetCarrerContentList(pageNum, subCategory);
            pageList = carrerContentService.GetPageList(pageNum);
        }

        model.addAttribute("subCategory",subCategory);
        model.addAttribute("CarrerContentDtoList",carrerContentDtoList);
        model.addAttribute("pageList",pageList);
        model.addAttribute("currentPageNum",pageNum);

        return "/Carrer/CarrerBoard";
    }

    @GetMapping("/Carrer/CarrerShow/{contentId}")
    public String ShowSingleCarrer(@PathVariable("contentId") Long id, Model model){
        CarrerContentDto carrerContentDto = carrerContentService.GetContent(id);

        model.addAttribute("CarrerContentDto",carrerContentDto);
        return "/Carrer/CarrerShow";
    }


    @RequestMapping("/Carrer/CarrerCreate")
    public String WriteSingleCarrer(Model model, CarrerContentDto carrerContentDto) {
        if (carrerContentDto.getTitle() == null) {
            //처음 만들러 들어온 상황
            return "/Carrer/CarrerCreate";
        }
        else{
            //다 작성 후 만들기 버튼 클릭한 상황
            carrerContentService.SaveCarrerContent(carrerContentDto);
            return "redirect:/Carrer/CarrerBoard";
        }

    }

    @GetMapping("/Carrer/CarrerModify/{contentId}")
    public String ModifySingleCarrerShow(@PathVariable("contentId") Long id,Model model){
        CarrerContentDto carrerContentDto = carrerContentService.GetContent(id);
        model.addAttribute("CarrerContentDto",carrerContentDto);
        return "Carrer/CarrerModify";
    }

    @GetMapping("/Carrer/CarrerModifyUpdate/{contentId}")
    public String ModifySingleCarrerUpdate(CarrerContentDto carrerContentDto){
        carrerContentService.SaveCarrerContent(carrerContentDto);
        return "redirect:/Carrer/CarrerBoard";
    }


    @GetMapping("/Carrer/CarrerDelete/{contentId}")
    public String DeleteSingleCarrer(@PathVariable("contentId") Long id){
        carrerContentService.DeleteCarrerContent(id);

        return "redirect:/Carrer/CarrerBoard";
    }

    @GetMapping("/Carrer/CarrerSearch")
    public String SearchCarrer(@RequestParam(value = "keyword") String keyword,Model model, HttpServletRequest request){
        String subCategory = request.getParameter("subCategory");

        List<CarrerContentDto> carrerContentDtoList;
        if (subCategory == "")
            carrerContentDtoList = carrerContentService.SearchCarrerContents(keyword, "total");
        else
            carrerContentDtoList = carrerContentService.SearchCarrerContents(keyword, subCategory);

        model.addAttribute("CarrerContentDtoList", carrerContentDtoList);
        return "Carrer/CarrerBoard";
    }
}
