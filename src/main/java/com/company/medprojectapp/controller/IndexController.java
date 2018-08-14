package com.company.medprojectapp.controller;

import com.company.medprojectapp.services.MedService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final MedService medService;

    public IndexController(MedService medService) {
        this.medService = medService;
    }

    @GetMapping
    @RequestMapping({"","/","/index","index"})
    public String getIndexPage(Model model){
        model.addAttribute("medicines", medService.getMedicines() );

        return "index";
    }

}
