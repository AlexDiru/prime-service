package com.alexdiru.primeservice;

import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

public class PrimeResponse {
    private int initial;
    private List<Integer> primes;

    public PrimeResponse(int initial, List<Integer> primes) {
        this.initial = initial;
        this.primes = primes;
    }

    public int getInitial() {
        return initial;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public List<Integer> getPrimes() {
        return primes;
    }

    public void setPrimes(List<Integer> primes) {
        this.primes = primes;
    }
}
