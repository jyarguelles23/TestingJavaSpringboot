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
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    // Con BDD
    @Mock
    SpecialtyRepository specialtyRepository;
    @InjectMocks
    SpecialitySDJpaService service;

    //Argument Matchers por el any hay para todo tipo de datos
    @Test
    void DeleteByObject(){
        //Given
        Speciality speciality=new Speciality();
        //When
        service.delete(speciality);
        //Then
        then(specialtyRepository).should().delete(any(Speciality.class));

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
        //Given
        Speciality speciality = new Speciality();

        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));
         //When
        Speciality foundSpecialty = service.findById(1L);

        //Then
        assertThat(foundSpecialty).isNotNull();
        then(specialtyRepository).should().findById(anyLong());
       // then(specialtyRepository).should(times(1)).findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteById(){
        //When
        service.deleteById(1L);

        //then
        then(specialtyRepository).should().deleteById(anyLong());
    }
    @Test
    void testDelete(){
        //When
        service.delete(new Speciality());
        //Then

        then(specialtyRepository).should().delete(any());
    }

    @Test
    void deleteById2Times() {
        //Given

        //When
        service.deleteById(1l);
        service.deleteById(1l);
        //Then
        then(specialtyRepository).should(times(2)).deleteById(1l);

    }

    @Test
    void deleteByIdAtLeast() {
        //Given
        //When
        service.deleteById(1l);
        service.deleteById(1l);
        //Then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1l);

    }

    @Test
    void deleteByIdAtMost() {
        //Given/
        //When
        service.deleteById(1l);
        service.deleteById(1l);
        //Then
        then(specialtyRepository).should(atMost(5)).deleteById(1l);

    }

    @Test
    void deleteByIdNever() {
        //Given
        //When
        service.deleteById(1l);
        service.deleteById(1l);

        //Then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1l);

        then(specialtyRepository).should(never()).deleteById(5l);

    }
}