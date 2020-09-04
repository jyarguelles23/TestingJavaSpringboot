package com.vicsystems.testingspringboot.Controllers;

import com.vicsystems.testingspringboot.Model.Owner;
import com.vicsystems.testingspringboot.Repositories.OwnerRepository;
import com.vicsystems.testingspringboot.Repositories.SpecialtyRepository;
import com.vicsystems.testingspringboot.Services.OwnerService;
import com.vicsystems.testingspringboot.Services.springdatajpa.OwnerSDJpaService;
import com.vicsystems.testingspringboot.Services.springdatajpa.SpecialitySDJpaService;
import com.vicsystems.testingspringboot.fauxspring.BindingResult;
import com.vicsystems.testingspringboot.fauxspring.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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

    @BeforeEach
    void setUp() {
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
                .willAnswer(invocation -> {
                    List<Owner> owners = new ArrayList<>();

                    String name = invocation.getArgument(0);

                    if (name.equals("%Buck%")) {
                        owners.add(new Owner(1l, "Joe", "Buck"));
                        return owners;
                    } else if (name.equals("%DontFindMe%")) {
                        return owners;
                    } else if (name.equals("%FindMe%")) {
                        owners.add(new Owner(1l, "Joe", "Buck"));
                        owners.add(new Owner(2l, "Joe2", "Buck2"));
                        return owners;
                    }

                    throw new RuntimeException("Invalid Argument");
                });
    }

    @Test
    void processFindFormWildcardFound() {
        //given
        Owner owner = new Owner(1l, "Joe", "FindMe");

        //when
        String viewName = ownerController.processFindForm(owner, bindingResult, Mockito.mock(Model.class));

        //then
        assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);
    }

    @Test
    void processFindFormWildcardStringAnnotation() {
        //given
        Owner owner = new Owner(1l, "Joe", "Buck");

        //when
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        //then
        assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
    }


    @Test
    void processFindFormWildcardNotFound() {
        //given
        Owner owner = new Owner(1l, "Joe", "DontFindMe");

        //when
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        //then
        assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
    }


    //Argument Captor Example
   /* @Test
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
    }*/

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