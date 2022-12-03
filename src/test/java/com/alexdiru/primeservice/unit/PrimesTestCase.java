package com.alexdiru.primeservice.unit;

import java.util.List;

class PrimesTestCase {
    private int initial;
    private List<Integer> primes;

    public PrimesTestCase(int initial, List<Integer> primes) {
        this.initial = initial;
        this.primes = primes;
    }

    public int getInitial() {
        return initial;
    }

    public List<Integer> getPrimes() {
        return primes;
    }

    public String toString() {
        return "Test Case, Initial = " + initial;
    }
}
