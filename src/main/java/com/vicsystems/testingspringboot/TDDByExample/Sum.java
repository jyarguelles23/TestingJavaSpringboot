package com.vicsystems.testingspringboot.TDDByExample;

public class Sum implements Expression {
    public Money augmend;
    public Money addmend;

    public Sum(Money augmend,Money addmend){
        this.augmend=augmend;
        this.addmend=addmend;
    }

    public Money reduce(String to) {
        int amount = augmend.amount + addmend.amount;
        return new Money(amount, to);
    }
}
