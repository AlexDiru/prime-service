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
        if (n <= 1) {
            return false; // 1 is not a prime number
        }

        // Do an even check, super simple and we don't want to waste cache memory on even numbers
        if (n % 2 == 0) {
            return false;
        }

        // TODO Check primeCache

        for (int i = 3; i < Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
