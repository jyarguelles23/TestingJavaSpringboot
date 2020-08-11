package com.vicsystems.testingspringboot.Controllers;


import com.vicsystems.testingspringboot.Services.VetService;
import com.vicsystems.testingspringboot.fauxspring.Model;

public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    public String listVets(Model model){

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
