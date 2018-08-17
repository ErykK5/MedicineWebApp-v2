package com.company.medprojectapp.controller;

import com.company.medprojectapp.model.Component;
import com.company.medprojectapp.services.ComponentService;
import com.company.medprojectapp.services.MedService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@Controller
public class ComponentController {

    private static final Logger log = Logger.getLogger( ComponentController.class.getName() );

    private  final MedService medService;

    private final ComponentService componentService;

    public ComponentController(ComponentService componentService, MedService medService ) {
        this.medService = medService;
        this.componentService = componentService;
    }

    @GetMapping("/med/components/{medId}")
    public String showComponentList(@PathVariable String medId, Model model ){
        model.addAttribute( "med", medService.findById( Long.valueOf( medId ) ) );
        return "med/components/list";
    }

    @GetMapping("/med/components/show/{medId}/{compId}")
    public String showSpecificComponent( @PathVariable String medId, @PathVariable String compId, Model model ){
        model.addAttribute( "comp", componentService.findByMedicineIdAndComponentId( Long.valueOf( medId ), Long.valueOf( compId ) ) );
        return "med/components/show";
    }

    @GetMapping("/med/components/update/{medId}/{compId}")
    public String updateComponent( @PathVariable String medId, @PathVariable String compId, Model model ){
        model.addAttribute( "comp", componentService.findByMedicineIdAndComponentId( Long.valueOf( medId ), Long.valueOf( compId ) ) );
        return "med/components/update";
    }

    @GetMapping("/med/components/new/{medId}")
    public String newComponent( @PathVariable String medId, Model model ){
        log.info( "Created component for medicine id: " + medId);
        Component c = new Component();
        c.setMedicine( medService.findById( Long.valueOf( medId ) ) );
        log.info( "newComponent: components medicine id: " + c.getMedicine().getId() );
        model.addAttribute( "comp", c );
        model.addAttribute( "med", medService.findById( Long.valueOf( medId ) ) );
        return "med/components/new";
    }

    @PostMapping("/med/components/{medId}")
    public String saveOrUpdateComponent( @PathVariable String medId, @ModelAttribute Component component ){
        component.setMedicine( medService.findById( Long.valueOf( medId ) ) );
        Component createdComponent = componentService.createComponent( component );
        log.info( "Created component id: " + createdComponent.getId() + " from medicine id: " + medId );
        return "redirect:/med/components/" + medId;
    }

    @GetMapping("/med/components/delete/{medId}/{compId}")
    public String deleteByIdComponent( @PathVariable String medId, @PathVariable String compId ){
        componentService.deleteById( Long.valueOf(medId), Long.valueOf(compId));
        return "redirect:/med/components/" + medId;
    }
}