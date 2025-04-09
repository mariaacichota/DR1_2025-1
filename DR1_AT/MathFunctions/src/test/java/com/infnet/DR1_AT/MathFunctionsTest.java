package com.infnet.DR1_AT;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import java.util.Arrays;

class MathFunctionsTest {

    @Property
    boolean multiplyByTwoShouldAlwaysReturnEven(@ForAll int number) {
        int result = MathFunctions.MultiplyByTwo(number);
        return result % 2 == 0;
    }

    @Property
    boolean generateMultiplicationTableShouldContainOnlyMultiples(
            @ForAll @IntRange(min = -1000, max = 1000) int number,
            @ForAll @IntRange(min = 1, max = 100) int limit) {
        int[] table = MathFunctions.GenerateMultiplicationTable(number, limit);
        return Arrays.stream(table).allMatch(n -> n % number == 0);
    }

    @Property
    boolean isPrimeShouldHaveOnlyTrivialDivisors(@ForAll("primes") int prime) {
        return MathFunctions.isPrime(prime)
                && IntStream.range(2, prime).noneMatch(i -> prime % i == 0);
    }

    @Provide
    Arbitrary<Integer> primes() {
        return Arbitraries.integers().between(2, 1000)
                .filter(MathFunctions::isPrime);
    }

    @Property
    boolean calculateAverageShouldBeWithinRange(
            @ForAll("nonEmptyArray") int[] array) {
        double avg = MathFunctions.CalculateAverage(array);
        int min = Arrays.stream(array).min().orElseThrow();
        int max = Arrays.stream(array).max().orElseThrow();
        return avg >= min && avg <= max;
    }

    @Provide
    Arbitrary<int[]> nonEmptyArray() {
        return Arbitraries.integers().between(-1000, 1000)
                .array(int[].class).ofMinSize(1).ofMaxSize(100);
    }
}