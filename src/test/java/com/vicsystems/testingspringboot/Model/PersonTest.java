package com.vicsystems.testingspringboot.Model;

import com.vicsystems.testingspringboot.ModelTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTests {
    @Test
    void groupedAssertions(){
        //given
        Person person =new Person(1l,"Jesus","Arguelles");

        //then
        assertAll("Test Props Set",
                () -> assertEquals(person.getFirstName(),"Jesus"),
                () -> assertEquals(person.getLastName(),"Arguelles"));
    }

    @Test
    void groupedAssertionsMSG(){
        //given
        Person person =new Person(1l,"Jesus","Arguelles");
        //then
        assertAll("Test Props Set",
                () -> assertEquals(person.getFirstName(),"Jesus", "First name failed"),
                () -> assertEquals(person.getLastName(),"Arguelles",()-> "Last name failed"));
    }
}