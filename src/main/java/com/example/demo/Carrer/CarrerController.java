package com.example.demo.Carrer;

import com.example.demo.DailyLife.DailyLifeContentDto;
import com.example.demo.DailyLife.DailyLifeContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarrerController {

    @Autowired
    private CarrerContentService carrerContentService;

    @RequestMapping("/Carrer/CarrerBoard")
    public String ShowCarrerBoard(Model model, @RequestParam(value = "Page",defaultValue = "1") Integer pageNum){
        List<CarrerContentDto> carrerContentDtoList = carrerContentService.GetCarrerContentList(pageNum);
        Integer[] pageList = carrerContentService.GetPageList(pageNum);

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
    public String WriteSingleCarrer(CarrerContentDto carrerContentDto){
        if (carrerContentDto.getTitle() == null)
            return "/Carrer/CarrerCreate";
        else{
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
    public String SearchCarrer(@RequestParam(value = "keyword") String keyword,Model model){
        List<CarrerContentDto> carrerContentDtoList = carrerContentService.SearchCarrerContents(keyword);
        System.out.println(carrerContentDtoList.size());
        model.addAttribute("CarrerContentDtoList", carrerContentDtoList);

        return "Carrer/CarrerBoard";
    }
}
