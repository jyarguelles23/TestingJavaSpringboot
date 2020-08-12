package com.vicsystems.testingspringboot.Controllers;

public class IndexController {

    public String index(){

        return "index";
    }

    public String oupsHandler(){
        throw new ValueNotFoundException();
    }
}
