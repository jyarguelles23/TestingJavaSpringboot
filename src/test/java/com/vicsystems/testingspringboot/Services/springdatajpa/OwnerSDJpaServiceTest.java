package com.vicsystems.testingspringboot.Services.springdatajpa;

import com.vicsystems.testingspringboot.Model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Disabled(value="Desahibilitado hasta que sepa ahcer mocking")
class OwnerSDJpaServiceTest {
//Skipping Junit test
    OwnerSDJpaService service;
    @BeforeEach
    void setUp() {
        service= new OwnerSDJpaService(null,null,null);
    }

    @Disabled
    @Test
    void findByLastName() {
        Owner foundOwner=service.findByLastName("Jes");
    }

    @Test
    void findAllByLastNameLike() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}