package com.vicsystems.testingspringboot.Model;

import com.vicsystems.testingspringboot.ModelRepeatedTest;
import org.junit.jupiter.api.*;


public class PersonRepeatedTest implements ModelRepeatedTest {

    @RepeatedTest(value= 10,name="{displayName} : {currentRepetition} - {totalRepitions")
    @DisplayName("RepeatedTest")

    void myRepeatedTest(){
        System.out.println("repitiendo");
    }

    @RepeatedTest(5)
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());

    }

    @RepeatedTest(value= 10,name="{displayName} : {currentRepetition} - {totalRepitions")
    @DisplayName("RepeatedTest")
    void myAssigmentRepeated() {

    }
}
