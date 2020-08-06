package com.vicsystems.testingspringboot.TDDByExample;

import java.util.Map;

public abstract class Money {

    protected int amount;
    protected String currency;

    public Money(int amount,String currency) {
        this.amount = amount;
        this.currency=currency;
    }
    public abstract Money times(int amount);

   public String currency(){
        return currency;
    }

    public static Money dollar(int amount){
        return new Dollar(amount,"USD");
    }

    public static Money franc(int amount){
        return new Franc(amount,"CHF");
    }

    public boolean equals(Object object) {
        Money money = (Money) object;
        //NO es lo mismo 5 francos que 5 dolares y en el equals la prueba no pasa porque les dice que son iguales para evitar eso se hace el sgte codigo
        return amount == money.amount && this.getClass().equals(object.getClass());
    }

}
