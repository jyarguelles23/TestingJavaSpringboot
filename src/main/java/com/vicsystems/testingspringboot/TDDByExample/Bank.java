package com.vicsystems.testingspringboot.TDDByExample;

import java.util.HashMap;

public class Bank   {

    HashMap<Pair,Integer> rateMap= new HashMap<>();
    public Money reduce(Expression source, String toCurrency) {
        return source.reduce(this,toCurrency);
    }

    public int rate(String from,String to){
        return (from.equals(to)) ? 1 : rateMap.get(new Pair(from,to));
    }

    public void addRate(String from, String to, int rate) {
        rateMap.put(new Pair(from,to),rate);
    }
}
