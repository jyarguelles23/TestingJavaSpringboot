package com.vicsystems.testingspringboot.TDDByExample;

public class Bank   {

    public Money reduce(Expression source, String toCurrency) {
        return source.reduce(toCurrency);
    }
}
