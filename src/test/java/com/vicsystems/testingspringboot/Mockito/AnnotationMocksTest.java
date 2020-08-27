package com.vicsystems.testingspringboot.Mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

public class AnnotationMocksTest {

    @Mock
    Map<String,Object> mapMock;

    @BeforeEach
    void Setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testMock(){
        mapMock.put("keyvalue","foo");
    }
}
