package com.vicsystems.testingspringboot.Mockito.InjectingMocks;

import com.vicsystems.testingspringboot.Model.Speciality;
import com.vicsystems.testingspringboot.Repositories.SpecialtyRepository;
import com.vicsystems.testingspringboot.Services.springdatajpa.SpecialitySDJpaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;
    @InjectMocks
    SpecialitySDJpaService service;

    //Argument Matchers por el any hay para todo tipo de datos
    @Test
    void DeleteByObject(){
        Speciality speciality=new Speciality();
        service.delete(speciality);
        verify(specialtyRepository).delete(any(Speciality.class));
    }

    //Returning values from repositories
    @Test
    void FindById(){
        Speciality speciality=new Speciality();

        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

        Speciality foundSpeciality=service.findById(1L);

        assertThat(foundSpeciality).isNotNull();

        verify(specialtyRepository).findById(1L);
    }

    //BDD MOckito
    @Test
    void findByIdBddTest() {
        Speciality speciality = new Speciality();

        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        Speciality foundSpecialty = service.findById(1L);

        assertThat(foundSpecialty).isNotNull();

        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    void deleteById(){
        service.deleteById(1L);
    }
    @Test
    void testDelete(){
        service.delete(new Speciality());
    }

    @Test
    void deleteById2Times() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository, times(2)).deleteById(1l);
    }

    @Test
    void deleteByIdAtLeast() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository, atLeastOnce()).deleteById(1l);
    }

    @Test
    void deleteByIdAtMost() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository, atMost(5)).deleteById(1l);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository, atLeastOnce()).deleteById(1l);

        verify(specialtyRepository, never()).deleteById(5L);
    }
}