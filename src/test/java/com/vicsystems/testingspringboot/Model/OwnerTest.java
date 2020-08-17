package com.vicsystems.testingspringboot.Model;

import com.vicsystems.testingspringboot.CustomArgsProvider;
import com.vicsystems.testingspringboot.ModelTests;
import com.vicsystems.testingspringboot.fauxspring.Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
//@Tag("Model")
class OwnerTest implements ModelTests {

    @Test
    void dependentAssertions() {

        Owner owner = new Owner(1l, "Joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("1231231234");

        assertAll("Properties Test",
                () -> assertAll("Person Properties",
                        () -> assertEquals("Joe", owner.getFirstName(), "First Name Did not Match"),
                        () -> assertEquals("Buck", owner.getLastName())),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Key West", owner.getCity(), "City Did Not Match"),
                        () -> assertEquals("1231231234", owner.getTelephone())
                ));
      //Assert That from hamcrest see this library also yes
        assertThat(owner.getCity(), is("Key West"));
    }

    @DisplayName("Value Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ValueSource(strings ={"Spring","Boot","Framework"})
    void testValueSource(String val){
        System.out.println(val);
    }

    @DisplayName("Enum Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType){
        System.out.println(ownerType);
    }

    @DisplayName("CSV Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvSource({
            "FL,1,1",
            "HAB,2,2",
            "OH,3,3",
            "MI,1,1"
    })
    void csvInputTest(String stateName,int val1,int val2){
        System.out.println(stateName+" : "+ val1+ " : "+ val2);
    }

    @DisplayName("CSV File Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvFileSource(resources = "/input.csv",numLinesToSkip = 1)
    void csvFromFileTest(String stateName,int val1,int val2){
        System.out.println(stateName+" : "+ val1+ " : "+ val2);
    }

    //Used to call DB,XML,JSON,messagequeue
    @DisplayName("Method Provider Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @MethodSource("getArgs")
    void fromMethodTest(String stateName,int val1,int val2){
        System.out.println(stateName+" : "+ val1+ " : "+ val2);
    }

    static Stream<Arguments> getArgs(){
        return Stream.of(
                Arguments.of("FL",1,1),
                Arguments.of("HAB",5,2));
    }

    @DisplayName("Custom Provider Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomMethodTest(String stateName,int val1,int val2){
        System.out.println(stateName+" : "+ val1+ " : "+ val2);
    }


}