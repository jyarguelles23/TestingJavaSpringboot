package com.vicsystems.testingspringboot;

import com.vicsystems.testingspringboot.TDDByExample.Bank;
import com.vicsystems.testingspringboot.TDDByExample.Expression;
import com.vicsystems.testingspringboot.TDDByExample.Money;
import com.vicsystems.testingspringboot.TDDByExample.Sum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoneyTest {

    @Test
    void testMultiplication() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
        Money fivef = Money.franc(5);
        assertEquals(Money.franc(10), fivef.times(2));
        assertEquals(Money.franc(15), fivef.times(3));
    }

    @Test
    void testEquality() {
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(8));
        assertNotEquals(Money.dollar(5),Money.franc(5));
        assertEquals(Money.franc(5), Money.franc(5));

    }

    @Test
    void testCurrency(){
        assertEquals("USD",Money.dollar(1).currency());
        assertEquals("CHF",Money.franc(1).currency());
    }

    @Test
    void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augmend);
        assertEquals(five, sum.addmend);
    }

    @Test
    void testReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), result);
    }

    @Test
    void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1), result);
    }

    @Test
    void testReduceMOneyDifferentCurrency(){
        Bank bank = new Bank();
        bank.addRate("CHF","USD",2);
        Money result=bank.reduce(Money.franc(2),"USD");
        assertEquals(Money.dollar(1),result);
    }

    @Test
    void TestIdentityRate(){
        assertEquals(1,new Bank().rate("USD","USD"));
        assertEquals(1,new Bank().rate("CHF","CHF"));
    }
}
