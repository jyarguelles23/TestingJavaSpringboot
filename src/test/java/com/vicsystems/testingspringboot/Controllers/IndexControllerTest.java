package com.vicsystems.testingspringboot.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Junit Assertions
class IndexControllerTest {

    IndexController controller;
    @BeforeEach
    void setUp() {
        controller=new IndexController();
    }

    @DisplayName("Testing index Method")
    @Test
    void index() {
        //assertEquals("index",controller.index());
        //assertEquals("index",controller.index(),"Wrong view returned");
        assertEquals("index",controller.index(), () -> "This is some expensive message");
    }

    @DisplayName("Test OupsHandlerException")
    @Test
    void oupsHandler() {
        assertTrue("notimplemented".equals(controller.oupsHandler()), () -> "This is some expensive message");
    }

}