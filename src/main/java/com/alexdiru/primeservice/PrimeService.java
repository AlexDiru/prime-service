package com.alexdiru.primeservice;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrimeService {

    public List<Integer> getPrimes(int initial) {

        // TODO check initialCache

        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= initial; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }

        return primes;
    }

    private boolean isPrime(int n) {
        // TODO Check primeCache

        boolean isPrime = PrimeUtil.isPrime(n);

        // TODO If the number is odd, we cache it, calculation for even isn't worth being cached

        return isPrime;
    }
}
