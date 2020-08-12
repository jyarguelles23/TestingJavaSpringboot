package com.vicsystems.testingspringboot.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/*Ver tambien como se hacen las pruebas en DEV o Prod y con Jenkins o Circle CI*/
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
        //Estudiar Libreria AssertJ se puede mezclar con JUnit
        assertThat(controller.index()).isEqualTo("index");
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
    {
        //Este es multihilo por lo q no espera los 5 segundos para ejecutarse
        assertTimeoutPreemptively(Duration.ofMillis(100),() -> {
            Thread.sleep(5000);
            System.out.println("Ejecute 1232312321312");
        });
    }

    //Leer mas sobre assume TRue ya que retorna un optional
    @Test
    void testAssumptionTrue() {
     assumeTrue("Yasser".equalsIgnoreCase("Jesus"));
    }

    @Test
    void testAssumptionTrueIsTrue() {
        assumeTrue("Yasser".equalsIgnoreCase("Yasser"));
    }
    @EnabledOnOs(OS.MAC)
    @Test
    void testMeOnMacOS() {
    }
    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testMeOnWindows() {

    }
    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testMeOnJava8() {
    }
    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testMeOnJava11() {
    }
 /*Ver como se hace este en windows*/
    @EnabledIfEnvironmentVariable(named = "USER",matches = "yasser")
    @Test
    void testMeIfUserYasser() {
    }

    @EnabledIfEnvironmentVariable(named = "USER",matches = "Fred")
    @Test
    void testMeIfUserFred() {
    }



}