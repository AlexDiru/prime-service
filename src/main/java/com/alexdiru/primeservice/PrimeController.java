package com.alexdiru.primeservice;

import com.fasterxml.jackson.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/primes")
public class PrimeController {

    @Autowired
    public PrimeService primeService;

    @GetMapping(value = "/{initial}", produces = { "application/json", "application/xml" })
    public PrimeResponse getPrimeResponse(
            @PathVariable(value="initial") int initial,
            @RequestParam("algorithm") Optional<PrimeService.Algorithm> algorithm) throws ExecutionException {
        List<Integer> primes = primeService.getPrimes(initial, algorithm.orElse(PrimeService.Algorithm.SIMPLE_PARALLEL));

        var primeResponse = new PrimeResponse(initial, primes);
        return primeResponse;
    }
}
