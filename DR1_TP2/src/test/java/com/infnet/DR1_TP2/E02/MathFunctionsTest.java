package com.infnet.DR1_TP2.E02;

import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;

public class MathFunctionsTest {

    @Property
    void multiplyByTwoShouldBeGreaterOrEqual(@ForAll int number) {
        int result = MathFunctions.multiplyByTwo(number);
        Assertions.assertTrue(result >= number, "O dobro de um número deve ser maior ou igual ao próprio número");
    }

    @Property
    void multiplyByTwoShouldPreserveEvenNumbers(@ForAll @IntRange(min = Integer.MIN_VALUE / 2, max = Integer.MAX_VALUE / 2) int evenNumber) {
        evenNumber = evenNumber * 2;
        int result = MathFunctions.multiplyByTwo(evenNumber);
        Assertions.assertEquals(0, result % 2, "O dobro de um número par deve continuar sendo par");
    }
}