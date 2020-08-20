package com.example.demo.Study;

import com.example.demo.Carrer.CarrerContentDto;
import com.example.demo.Carrer.CarrerContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StudyController {

    @Autowired
    private StudyContentService studyContentService;

    @RequestMapping("/Study/StudyBoard")
    public String ShowStudyBoard(Model model, @RequestParam(value = "Page",defaultValue = "1") Integer pageNum, HttpServletRequest request){
        String subCategory = request.getParameter("subCategory");

        List<StudyContentDto> studyContentDtoList;
        Integer[] pageList;

        if(subCategory == null){
            studyContentDtoList = studyContentService.GetStudyContentList(pageNum,"total");
            pageList = studyContentService.GetPageList(pageNum);
        }
        else{
            studyContentDtoList = studyContentService.GetStudyContentList(pageNum, subCategory);
            pageList = studyContentService.GetPageList(pageNum);
        }

        model.addAttribute("subCategory",subCategory);
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

    @GetMapping("/Study/StudyModifyUpdate/{contentId}")
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
    public String SearchStudy(@RequestParam(value = "keyword") String keyword,Model model,HttpServletRequest request){
        String subCategory = request.getParameter("subCategory");

        List<StudyContentDto> studyContentDtoList;
        if (subCategory == "")
            studyContentDtoList = studyContentService.SearchStudyContents(keyword, "total");
        else
            studyContentDtoList = studyContentService.SearchStudyContents(keyword, subCategory);

        model.addAttribute("StudyContentDtoList", studyContentDtoList);

        return "Study/StudyBoard";
    }

}
