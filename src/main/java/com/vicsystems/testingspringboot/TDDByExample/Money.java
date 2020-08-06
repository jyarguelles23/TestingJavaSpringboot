package com.vicsystems.testingspringboot.TDDByExample;

public abstract class Money {

    protected int amount;

    public abstract Money times(int amount);
    public static Dollar dollar(int amount){
        return new Dollar(amount);
    }
    public static Franc franc(int amount){
        return new Franc(amount);
    }
    public boolean equals(Object object) {
        Money money = (Money) object;
        //NO es lo mismo 5 francos que 5 dolares y en el equals la prueba no pasa porque les dice que son iguales para evitar eso se hace el sgte codigo
        return amount == money.amount && this.getClass().equals(object.getClass());
    }

}
