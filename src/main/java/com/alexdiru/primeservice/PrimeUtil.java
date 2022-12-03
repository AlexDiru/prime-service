package com.alexdiru.primeservice;

import java.util.ArrayList;
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
