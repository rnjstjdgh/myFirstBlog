package com.example.demo.Incidental;

import com.example.demo.Category.CategoryDto;
import com.example.demo.Category.CategoryService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IncidentalController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public String index(){
        return "/Incidental/index";
    }

    @RequestMapping("/leftSidebar")
    public String loadSidebar(Model model) {
        //카테고리를 다 가져와서 leftSidebar에 다 전달해줘야한다.
        List<CategoryDto> categoryDtoList = categoryService.GetCategoryList();

        model.addAttribute("categoryList",categoryDtoList);

        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/header")
    public String loadHeader() {
        return "/Incidental/header";
    }


    @RequestMapping("/DailyLife/leftSidebar")
    public String DailyLife_loadSidebar1(Model model) {
        List<CategoryDto> categoryDtoList = categoryService.GetCategoryList();

        model.addAttribute("categoryList",categoryDtoList);
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/DailyLife/header")
    public String DailyLife_loadHeader1() {
        return "/Incidental/header";
    }

    @RequestMapping("/DailyLife/DailyLifeShow/leftSidebar")
    public String DailyLife_loadSidebar2(Model model) {
        List<CategoryDto> categoryDtoList = categoryService.GetCategoryList();

        model.addAttribute("categoryList",categoryDtoList);
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/DailyLife/DailyLifeShow/header")
    public String DailyLife_loadHeader2() {
        return "/Incidental/header";
    }

    @RequestMapping("/DailyLife/DailyLifeModify/leftSidebar")
    public String DailyLife_loadSidebar3(Model model) {
        List<CategoryDto> categoryDtoList = categoryService.GetCategoryList();

        model.addAttribute("categoryList",categoryDtoList);
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/DailyLife/DailyLifeModify/header")
    public String DailyLife_loadHeader3() {
        return "/Incidental/header";
    }

    @RequestMapping("/Carrer/leftSidebar")
    public String Carrer_loadSidebar1(Model model) {
        List<CategoryDto> categoryDtoList = categoryService.GetCategoryList();

        model.addAttribute("categoryList",categoryDtoList);
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/Carrer/header")
    public String Carrer_loadHeader1() {
        return "/Incidental/header";
    }

    @RequestMapping("/Carrer/CarrerShow/leftSidebar")
    public String Carrer_loadSidebar2(Model model) {
        List<CategoryDto> categoryDtoList = categoryService.GetCategoryList();

        model.addAttribute("categoryList",categoryDtoList);
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/Carrer/CarrerShow/header")
    public String Carrer_loadHeader2() {
        return "/Incidental/header";
    }

    @RequestMapping("/Carrer/CarrerModify/leftSidebar")
    public String Carrer_loadSidebar3(Model model) {
        List<CategoryDto> categoryDtoList = categoryService.GetCategoryList();

        model.addAttribute("categoryList",categoryDtoList);
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/Carrer/CarrerModify/header")
    public String Carrer_loadHeader3() {
        return "/Incidental/header";
    }

    @RequestMapping("/Study/leftSidebar")
    public String Study_loadSidebar1(Model model) {
        List<CategoryDto> categoryDtoList = categoryService.GetCategoryList();

        model.addAttribute("categoryList",categoryDtoList);
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/Study/header")
    public String Study_loadHeader1() {
        return "/Incidental/header";
    }

    @RequestMapping("/Study/StudyShow/leftSidebar")
    public String Study_loadSidebar2(Model model) {
        List<CategoryDto> categoryDtoList = categoryService.GetCategoryList();

        model.addAttribute("categoryList",categoryDtoList);
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/Study/StudyShow/header")
    public String Study_loadHeader2() {
        return "/Incidental/header";
    }

    @RequestMapping("/Study/StudyModify/leftSidebar")
    public String Study_loadSidebar3(Model model) {
        List<CategoryDto> categoryDtoList = categoryService.GetCategoryList();

        model.addAttribute("categoryList",categoryDtoList);
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/Study/StudyModify/header")
    public String Study_loadHeader3() {
        return "/Incidental/header";
    }
}
