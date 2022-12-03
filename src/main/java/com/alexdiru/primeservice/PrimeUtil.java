package com.alexdiru.primeservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class PrimeUtil {
    public static List<Integer> calculatePrimes(int initial) {
        return calculatePrimesInRange(2, initial);
    }

    public static List<Integer> calculatePrimesInParallel(int initial, int numThreads) throws InterruptedException, ExecutionException {
        if (initial < 10) {
            return calculatePrimes(initial);
        }

        if (numThreads > initial) {
            numThreads = initial;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        int start = 0;
        int step = (int)Math.ceil((double)initial / numThreads); // The array might not be exactly divisible by numThreads
        int end = step;

        List<CalculatePrimesInRangeTask> tasks = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            tasks.add(new CalculatePrimesInRangeTask(start, end));
            start = end + 1;
            end += step;
            end = Math.min(end, initial);
        }

        List<Integer> results = new ArrayList<>();
        for (Future<List<Integer>> result : executorService.invokeAll(tasks)) {
            results.addAll(result.get());
        }

        return results;
    }

    public static List<Integer> calculatePrimesUsingSieve(int initial) {
        // Used for help:
        // https://stackoverflow.com/questions/10580159/sieve-of-atkin-explanation-and-java-example
        boolean[] flags = new boolean[initial + 1];
        Arrays.fill(flags, false);

        flags[2] = true;
        flags[3] = true;

        int sqrtInitial = (int)Math.ceil(Math.sqrt(initial));

        // Use the sieve algorithm to find all the primes up to initial
        for (int x = 1; x <= sqrtInitial; x++) {
            for (int y = 1; y <= sqrtInitial; y++) {
                // First polynomial value
                int n = 4 * x * x + y * y;
                if (n <= initial && (n % 12 == 1 || n % 12 == 5)) {
                    flags[n] = !flags[n];
                }

                // Second polynomial value
                n = 3 * x * x + y * y;
                if (n <= initial && n % 12 == 7) {
                    flags[n] = !flags[n];
                }

                // Third polynomial value
                n = 3 * x * x - y * y;
                if (x > y && n <= initial && n % 12 == 11) {
                    flags[n] = !flags[n];
                }
            }
        }

        // Remove all perfect squares
        for (int i = 5; i <= sqrtInitial; i++) {
            if (flags[i]) {
                for (int j = i * i; j <= initial; j += i * i) {
                    flags[j] = false;
                }
            }
        }

        // Add all primes to a list
        List<Integer> primes = new ArrayList<>();
        for (int i = 0; i < flags.length; i++) {
            if (flags[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    private record CalculatePrimesInRangeTask(int fromInclusive, int toInclusive) implements Callable<List<Integer>> {
        @Override
            public List<Integer> call() {
                return calculatePrimesInRange(fromInclusive, toInclusive);
            }
        }

    private static List<Integer> calculatePrimesInRange(int fromInclusive, int toInclusive) {
        List<Integer> primes = new ArrayList<>();
        for (int i = fromInclusive; i <= toInclusive; i++) {
            if (PrimeUtil.isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        if (n % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}