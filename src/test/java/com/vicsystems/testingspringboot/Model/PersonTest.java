package com.vicsystems.testingspringboot.Model;

import com.vicsystems.testingspringboot.ModelTests;
import org.junit.jupiter.api.*;

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

    @RepeatedTest(value= 10,name="{displayName} : {currentRepetition} - {totalRepitions")
    @DisplayName("RepeatedTest")
    @Test
    void myRepeatedTest(){
        System.out.println("repitiendo");
    }

    @RepeatedTest(5)
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());

    }
}