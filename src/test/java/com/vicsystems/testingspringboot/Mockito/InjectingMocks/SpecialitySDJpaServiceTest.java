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
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    // Con BDD
    //@Mock(lenient = true)
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

    @Test
    void testDoThrow() {
        doThrow(new RuntimeException("boom")).when(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

        verify(specialtyRepository).delete(any());
    }

    @Test
    void testFindByIDThrows() {
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("boom"));

        assertThrows(RuntimeException.class, () -> service.findById(1L));

        then(specialtyRepository).should().findById(1L);
    }

    @Test
    void testDeleteBDD() {
        willThrow(new RuntimeException("boom")).given(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

        then(specialtyRepository).should().delete(any());
    }

    @Test
    void testSaveLambda() {
        //given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription(MATCH_ME);

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1L);

        //need mock to only return on match MATCH_ME string
        given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpecialty);

        //when
        Speciality returnedSpecialty = service.save(speciality);

        //then
        assertThat(returnedSpecialty.getId()).isEqualTo(1L);
    }

    @Test
    void testSaveLambdaNoMatch() {
        //given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription("Not a match");

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1L);

        //need mock to only return on match MATCH_ME string
        given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpecialty);

        //when
        Speciality returnedSpecialty = service.save(speciality);

        //then
        assertNull(returnedSpecialty);
    }

}