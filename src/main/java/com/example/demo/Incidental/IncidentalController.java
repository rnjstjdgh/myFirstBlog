package com.example.demo.Incidental;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IncidentalController {

    @GetMapping("/")
    public String index(){
        return "/Incidental/index";
    }

    @RequestMapping("/leftSidebar")
    public String loadSidebar() {
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/header")
    public String loadHeader() {
        return "/Incidental/header";
    }


    @RequestMapping("/DailyLife/leftSidebar")
    public String DailyLife_loadSidebar1() {
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/DailyLife/header")
    public String DailyLife_loadHeader1() {
        return "/Incidental/header";
    }

    @RequestMapping("/DailyLife/DailyLifeShow/leftSidebar")
    public String DailyLife_loadSidebar2() {
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/DailyLife/DailyLifeShow/header")
    public String DailyLife_loadHeader2() {
        return "/Incidental/header";
    }

    @RequestMapping("/DailyLife/DailyLifeModify/leftSidebar")
    public String DailyLife_loadSidebar3() {
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/DailyLife/DailyLifeModify/header")
    public String DailyLife_loadHeader3() {
        return "/Incidental/header";
    }

    @RequestMapping("/Carrer/leftSidebar")
    public String Carrer_loadSidebar1() {
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/Carrer/header")
    public String Carrer_loadHeader1() {
        return "/Incidental/header";
    }

    @RequestMapping("/Carrer/CarrerShow/leftSidebar")
    public String Carrer_loadSidebar2() {
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/Carrer/CarrerShow/header")
    public String Carrer_loadHeader2() {
        return "/Incidental/header";
    }

    @RequestMapping("/Carrer/CarrerModify/leftSidebar")
    public String Carrer_loadSidebar3() {
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/Carrer/CarrerModify/header")
    public String Carrer_loadHeader3() {
        return "/Incidental/header";
    }

    @RequestMapping("/Study/leftSidebar")
    public String Study_loadSidebar1() {
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/Study/header")
    public String Study_loadHeader1() {
        return "/Incidental/header";
    }

    @RequestMapping("/Study/StudyShow/leftSidebar")
    public String Study_loadSidebar2() {
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/Study/StudyShow/header")
    public String Study_loadHeader2() {
        return "/Incidental/header";
    }

    @RequestMapping("/Study/StudyModify/leftSidebar")
    public String Study_loadSidebar3() {
        return "/Incidental/leftSidebar";
    }

    @RequestMapping("/Study/StudyModify/header")
    public String Study_loadHeader3() {
        return "/Incidental/header";
    }
}
