package com.vicsystems.testingspringboot.Mockito;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class InLineMockTest {

    @Test
    void testInlineMock(){
        Map mapMock=mock(Map.class);
        assertEquals(mapMock.size(),0);
    }

}
