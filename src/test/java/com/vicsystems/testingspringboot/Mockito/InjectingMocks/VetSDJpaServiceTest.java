package com.vicsystems.testingspringboot.Mockito.InjectingMocks;

import com.vicsystems.testingspringboot.Repositories.VetRepository;
import com.vicsystems.testingspringboot.Services.springdatajpa.VetSDJpaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {

    @Mock
    VetRepository vetRepository;
    @InjectMocks
    VetSDJpaService service;

    @Test
    void deleteById(){
        service.deleteById(1L);
        verify(vetRepository).deleteById(1l);

    }

}