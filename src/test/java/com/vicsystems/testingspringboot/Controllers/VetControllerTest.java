package com.vicsystems.testingspringboot.Controllers;

import com.vicsystems.testingspringboot.ControllerTests;
import com.vicsystems.testingspringboot.Model.Speciality;
import com.vicsystems.testingspringboot.Model.Vet;
import com.vicsystems.testingspringboot.Services.SpecialtyService;
import com.vicsystems.testingspringboot.Services.VetService;
import com.vicsystems.testingspringboot.Services.map.SpecialityMapService;
import com.vicsystems.testingspringboot.Services.map.VetMapService;
import com.vicsystems.testingspringboot.fauxspring.Model;
import com.vicsystems.testingspringboot.fauxspring.ModelMapImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
//@Tag("controller")
class VetControllerTest implements ControllerTests {

    VetService service;
    SpecialtyService specialtyService;
    VetController controller;
    @BeforeEach
    void setUp() {

        specialtyService=new SpecialityMapService();
        service=new VetMapService(specialtyService);
        controller = new VetController(service);
        Vet vet1=new Vet(1L,"yasser","arguelles",null);
        Vet vet2=new Vet(2L,"jesus","arguelles",null);
        service.save(vet1);
        service.save(vet2);
    }

    @Test
    void listVets() {
      Model model= new ModelMapImpl();
      String view= controller.listVets(model);
      assertThat("vets/index").isEqualTo(view);

      Set modelAtributte=(Set)((ModelMapImpl) model).getMap().get("vets");
      assertThat(modelAtributte.size()).isEqualTo(2);
    }
}