package com.example.demo.DailyLife;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DailyLifeController {

    @Autowired
    private DailyLifeContentService dailyLifeContentService;

    @RequestMapping("/DailyLife/DailyLifeBoard")
    public String ShowDailyLifeBoard(Model model, @RequestParam(value = "Page",defaultValue = "1") Integer pageNum){
        List<DailyLifeContentDto> dailyLifeContentDtoList = dailyLifeContentService.GetDailyLifeContentList(pageNum);
        Integer[] pageList = dailyLifeContentService.GetPageList(pageNum);

        model.addAttribute("dailyLifeContentDtoList",dailyLifeContentDtoList);
        model.addAttribute("pageList",pageList);
        model.addAttribute("currentPageNum",pageNum);

        return "/DailyLife/DailyLifeBoard";
    }

    @GetMapping("/DailyLife/DailyLifeShow/{contentId}")
    public String ShowSingleDailyLife(@PathVariable("contentId") Long id, Model model){
        DailyLifeContentDto dailyLifeContentDto = dailyLifeContentService.GetContent(id);

        model.addAttribute("dailyLifeContentDto",dailyLifeContentDto);
        return "/DailyLife/DailyLifeShow";
    }


    @RequestMapping("/DailyLife/DailyLifeCreate")
    public String WriteSingleDailyLife(DailyLifeContentDto dailyLifeContentDto){
        if (dailyLifeContentDto.getTitle() == null)
                    return "/DailyLife/DailyLifeCreate";
        else {
            dailyLifeContentService.SaveDailyLifeContent(dailyLifeContentDto);
            return "redirect:/DailyLife/DailyLifeBoard";
        }
    }

    @GetMapping("/DailyLife/DailyLifeModify/{contentId}")
    public String ModifySingleDailyLifeShow(@PathVariable("contentId") Long id,Model model){
        DailyLifeContentDto dailyLifeContentDto = dailyLifeContentService.GetContent(id);
        model.addAttribute("dailyLifeContentDto",dailyLifeContentDto);
        return "DailyLife/DailyLifeModify";
    }

    @GetMapping("/DailyLife/DailyLifeModifyUpdate/{contentId}")
    public String ModifySingleDailyLifeUpdate(DailyLifeContentDto dailyLifeContentDto){
        dailyLifeContentService.SaveDailyLifeContent(dailyLifeContentDto);
        return "redirect:/DailyLife/DailyLifeBoard";
    }

    @GetMapping("/DailyLife/DailyLifeDelete/{contentId}")
    public String DeleteSingleDailyLife(@PathVariable("contentId") Long id){
        dailyLifeContentService.DeleteDailyLifeContent(id);

        return "redirect:/DailyLife/DailyLifeBoard";
    }

    @GetMapping("/DailyLife/DailyLifeSearch")
    public String SearchDailyLife(@RequestParam(value = "keyword") String keyword,Model model){
        List<DailyLifeContentDto> dailyLifeContentDtoList = dailyLifeContentService.SearchDailyLifeContents(keyword);
        System.out.println(dailyLifeContentDtoList.size());
        model.addAttribute("dailyLifeContentDtoList", dailyLifeContentDtoList);

        return "DailyLife/DailyLifeBoard";
    }



}
