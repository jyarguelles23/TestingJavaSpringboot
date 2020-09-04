package com.vicsystems.testingspringboot.Controllers;

import com.vicsystems.testingspringboot.Model.Owner;
import com.vicsystems.testingspringboot.Repositories.OwnerRepository;
import com.vicsystems.testingspringboot.Repositories.SpecialtyRepository;
import com.vicsystems.testingspringboot.Services.OwnerService;
import com.vicsystems.testingspringboot.Services.springdatajpa.OwnerSDJpaService;
import com.vicsystems.testingspringboot.Services.springdatajpa.SpecialitySDJpaService;
import com.vicsystems.testingspringboot.fauxspring.BindingResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    public static final String OWNERS_CREATE_OR_UPDATE_OWNERFORM = "owners/createOrUpdateOwnerform";
    @Mock
    OwnerService ownerService;
    @InjectMocks
    OwnerController ownerController;

    @Mock
    BindingResult bindingResult;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    void processFindFormWildcardString() {
        //given
        Owner owner = new Owner(1l, "Joe", "Buck");
        List<Owner> ownerList = new ArrayList<>();
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(ownerList);

        //when
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        //then
        assertThat("%Buck%").isEqualToIgnoringCase(captor.getValue());
    }

    @Test
    void processFindFormWildcardStringAnnotation() {
        //given
        Owner owner = new Owner(1l, "Joe", "Buck");
        List<Owner> ownerList = new ArrayList<>();
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);

        //when
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        //then
        assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
    }

    @Test
    void processCreationFormHasErrors() {
        //given
        Owner owner =new Owner(1l,"Jim","Bob");
        given(bindingResult.hasErrors()).willReturn(true);
        //when
        String viewName = ownerController.processCreationForm(owner, bindingResult);
        //then
        assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNERFORM);
    }

    @Test
    void processCreationFormNoErrors() {
       // given
        Owner owner =new Owner(5l,"Jim","Bob");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any())).willReturn(owner);
        //when
        String viewName = ownerController.processCreationForm(owner, bindingResult);
        //then
        assertThat(viewName).isEqualToIgnoringCase("redirect:/owners/5");

    }

}