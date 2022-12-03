package com.alexdiru.primeservice;

import com.fasterxml.jackson.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class PrimeController {

    @Autowired
    public PrimeService primeService;

    @GetMapping("/primes/{initial}")
    public PrimeResponse getPrimeResponse(@PathVariable(value="initial") int initial) throws ExecutionException {
        List<Integer> primes = primeService.getPrimes(initial);

        var primeResponse = new PrimeResponse(initial, primes);
        return primeResponse;
    }
}
