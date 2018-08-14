package com.company.medprojectapp.controller;

import com.company.medprojectapp.model.Medicine;
import com.company.medprojectapp.services.MedService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MedicineController {

    private final MedService medService;

    public MedicineController(MedService medService) {
        this.medService = medService;
    }

    @GetMapping("med/show/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("med", medService.findById( new Long(id) ) );
        return "med/show";
    }

    @GetMapping("med/new")
    public String createMed( Model model ){
        model.addAttribute("med", new Medicine() );
        return "med/medform";
    }

    @PostMapping("med")
    public String saveMed(@ModelAttribute Medicine medicine){
        Medicine medicine1 = medService.createMed( medicine );
        return "redirect:/med/show/" + medicine1.getId() ;
    }

    @GetMapping("med/update/{id}")
    public String updateMed(@PathVariable String id, Model model){
        model.addAttribute("med", medService.findById( Long.valueOf( id ) ));
        return "med/medform";
    }

    @GetMapping("med/delete/{id}")
    public String deleteMed(@PathVariable String id ){
        medService.deleteMed( Long.valueOf( id ) );
        return "redirect:/";
    }
}