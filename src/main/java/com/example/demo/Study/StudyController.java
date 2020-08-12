package com.example.demo.Study;

import com.example.demo.Carrer.CarrerContentDto;
import com.example.demo.Carrer.CarrerContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudyController {

    @Autowired
    private StudyContentService studyContentService;

    @RequestMapping("/Study/StudyBoard")
    public String ShowStudyBoard(Model model, @RequestParam(value = "Page",defaultValue = "1") Integer pageNum){
        List<StudyContentDto> studyContentDtoList = studyContentService.GetStudyContentList(pageNum);
        Integer[] pageList = studyContentService.GetPageList(pageNum);

        model.addAttribute("StudyContentDtoList",studyContentDtoList);
        model.addAttribute("pageList",pageList);
        model.addAttribute("currentPageNum",pageNum);

        return "/Study/StudyBoard";
    }

    @GetMapping("/Study/StudyShow/{contentId}")
    public String ShowSingleStudy(@PathVariable("contentId") Long id, Model model){
        StudyContentDto studyContentDto = studyContentService.GetContent(id);

        model.addAttribute("StudyContentDto",studyContentDto);
        return "/Study/StudyShow";
    }


    @GetMapping("/Study/StudyCreate")
    public String WriteSingleStudy(StudyContentDto studyContentDto){
        if ( studyContentDto.getTitle() == null)
            return "/Study/StudyCreate";
        else{
            studyContentService.SaveStudyContent(studyContentDto);
            return "redirect:/Study/StudyBoard";
        }
    }

    @GetMapping("/Study/StudyModify/{contentId}")
    public String ModifySingleStudyShow(@PathVariable("contentId") Long id,Model model){
        StudyContentDto studyContentDto = studyContentService.GetContent(id);
        model.addAttribute("StudyContentDto",studyContentDto);
        return "Study/StudyModify";
    }

    @PutMapping("/Study/StudyModifyUpdate/{contentId}")
    public String ModifySingleStudyUpdate(StudyContentDto studyContentDto){
        studyContentService.SaveStudyContent(studyContentDto);
        return "redirect:/Study/StudyBoard";
    }

    @GetMapping("/Study/StudyDelete/{contentId}")
    public String DeleteSingleStudy(@PathVariable("contentId") Long id){
        studyContentService.DeleteStudyContent(id);

        return "redirect:/Study/StudyBoard";
    }

    @GetMapping("/Study/StudySearch")
    public String SearchStudy(@RequestParam(value = "keyword") String keyword,Model model){
        List<StudyContentDto> studyContentDtoList = studyContentService.SearchStudyContents(keyword);
        System.out.println(studyContentDtoList.size());
        model.addAttribute("StudyContentDtoList", studyContentDtoList);

        return "Study/StudyBoard";
    }

}
