package com.alexdiru.primeservice;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class PrimeService {

    public enum Algorithm {
        SIMPLE_PARALLEL,
        SIEVE_OF_ATKIN
    }

    public List<Integer> getPrimes(int initial, Algorithm algorithm) throws ExecutionException {
        // The cache is implemented for the straightforward algorithm, but not the Sieve
        switch (algorithm) {
            case SIEVE_OF_ATKIN -> { return PrimeUtil.calculatePrimesUsingSieve(initial); }
            default -> { return initialCache.get(initial); }
        }
    }

    // The cache could be Spring-ified, I chose to use Guava
    // If this scales to replicas, then each replica will have its own cache
    // I did consider implementing a cache to cache oddNumbers and whether they are primed, but chose not to for
    // readibility

    private LoadingCache<Integer, List<Integer>> initialCache = CacheBuilder.newBuilder()
            .expireAfterAccess(1, TimeUnit.MINUTES)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .initialCapacity(5)
            .maximumSize(50)
            .build(new CacheLoader<>() {
                @Override
                public List<Integer> load(Integer initial) throws ExecutionException, InterruptedException {
                    return PrimeUtil.calculatePrimesInParallel(initial, 8);
                }
            });
}
