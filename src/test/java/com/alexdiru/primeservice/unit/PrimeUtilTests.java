package com.alexdiru.primeservice.unit;

import com.alexdiru.primeservice.PrimeUtil;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class PrimeUtilTests {
    @Test
    public void whenPrimeNumber_shouldBeTrue() {
        assertTrue(PrimeUtil.isPrime(2));
        assertTrue(PrimeUtil.isPrime(3));
        assertTrue(PrimeUtil.isPrime(5));
        assertTrue(PrimeUtil.isPrime(7));
        assertTrue(PrimeUtil.isPrime(11));
    }

    @Test
    public void whenNotPrimeNumber_shouldBeFalse() {
        assertFalse(PrimeUtil.isPrime(1));
        assertFalse(PrimeUtil.isPrime(4));
        assertFalse(PrimeUtil.isPrime(9));
        assertFalse(PrimeUtil.isPrime(26));
        assertFalse(PrimeUtil.isPrime(55));
    }

    @Test
    public void whenNegative_shouldBeFalse() {
        assertFalse(PrimeUtil.isPrime(-1));
        assertFalse(PrimeUtil.isPrime(-2));
        assertFalse(PrimeUtil.isPrime(-200));
        assertFalse(PrimeUtil.isPrime(-5));
    }

    @ParameterizedTest
    @MethodSource(value = "primesTestCaseData")
    public void whenSingleThreaded_shouldCalculatePrimes(PrimesTestCase primesTestCase) {
        List<Integer> result = PrimeUtil.calculatePrimes(primesTestCase.getInitial());
        assertIterableEquals(primesTestCase.getPrimes(), result);
    }

    @ParameterizedTest
    @MethodSource(value = "primesTestCaseMultithreadedData")
    public void whenMultiThreaded_shouldCalculatePrimes(PrimesTestCase primesTestCase, int numThreads) throws ExecutionException, InterruptedException {
        List<Integer> result = PrimeUtil.calculatePrimesInParallel(primesTestCase.getInitial(), 2);
        assertIterableEquals(primesTestCase.getPrimes(), result);
    }

    @ParameterizedTest
    @MethodSource(value = "primesTestCaseData")
    public void whenSieve_shouldCalculatePrimes(PrimesTestCase primesTestCase) {
        List<Integer> result = PrimeUtil.calculatePrimesUsingSieve(primesTestCase.getInitial());
        assertIterableEquals(primesTestCase.getPrimes(), result);
    }

    // TODO read the longer test cases from a CSV
    private static List<PrimesTestCase> primesTestCaseData() {
        return List.of(
                new PrimesTestCase(11, List.of(2, 3, 5, 7, 11)),
                new PrimesTestCase(12, List.of(2, 3, 5, 7, 11)),
                new PrimesTestCase(1223, List.of(2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,
                        71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,
                        197,199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,293,307,311,313,317,331,337,
                        347,349,353,359,367,373,379,383,389,397,401,409,419,421,431,433,439,443,449,457,461,463,467,479,
                        487,491,499,503,509,521,523,541,547,557,563,569,571,577,587,593,599,601,607,613,617,619,631,641,
                        643,647,653,659,661,673,677,683,691,701,709,719,727,733,739,743,751,757,761,769,773,787,797,809,
                        811,821,823,827,829,839,853,857,859,863,877,881,883,887,907,911,919,929,937,941,947,953,967,971,
                        977,983,991,997,1009,1013,1019,1021,1031,1033,1039,1049,1051,1061,1063,1069,1087,1091,1093,1097,
                        1103,1109,1117,1123,1129,1151,1153,1163,1171,1181,1187,1193,1201,1213,1217,1223))
        );
    }

    private static List<Arguments> primesTestCaseMultithreadedData() {
        List<Arguments> args = new ArrayList<>();
        List<PrimesTestCase> testCases = primesTestCaseData();
        for (int i = 0; i < testCases.size(); i++) {
            for (int numThreads = 1; numThreads <= 12; numThreads++) {
                args.add(Arguments.arguments(testCases.get(i), numThreads));
            }
        }
        return args;
    }
}
