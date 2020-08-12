package com.vicsystems.testingspringboot.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

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

        //   assertTrue("notimplemented".equals(controller.oupsHandler()), () -> "This is some expensive message");
    }

    @DisplayName("Test OupsHandlerException2")
    @Test
    void oupsHandler2() {
        assertThrows(ValueNotFoundException.class,() ->{
           controller.oupsHandler();
        });
    }

    @Test
    void TestTimeOut()
    {
        //Este es de un mismo hilo por lo q espera 5 segundos para ejecutarse
        assertTimeout(Duration.ofMillis(100),() -> {
            Thread.sleep(5000);
            System.out.println("Ejecute");
        });
    }

    @Test
    void TestTimeOutPrempt()
    {//Este es multihilo por lo q no espera los 5 segundos para ejecutarse
        assertTimeoutPreemptively(Duration.ofMillis(100),() -> {
            Thread.sleep(5000);
            System.out.println("Ejecute 1232312321312");
        });
    }
}