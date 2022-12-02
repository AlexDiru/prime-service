package com.alexdiru.primeservice.unit;

import com.alexdiru.primeservice.PrimeUtil;
import org.junit.Test;

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
}
