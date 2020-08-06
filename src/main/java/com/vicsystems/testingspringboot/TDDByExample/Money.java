package com.vicsystems.testingspringboot.TDDByExample;

public class Money {

    protected int amount;

    public boolean equals(Object object) {
        Money money = (Money) object;
        //NO es lo mismo 5 francos que 5 dolares y en el equals la prueba no pasa porque les dice que son iguales para evitar eso se hace el sgte codigo
        return amount == money.amount && this.getClass().equals(object.getClass());
    }

}
