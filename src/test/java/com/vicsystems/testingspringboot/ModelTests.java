package com.vicsystems.testingspringboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("Models")
public interface ModelTests {

    @BeforeEach
    default void BeforeEachConsoleOutput(TestInfo testInfo){
        System.out.println(testInfo.getDisplayName() );
    }
}
