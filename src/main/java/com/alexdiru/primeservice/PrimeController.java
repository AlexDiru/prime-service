package com.alexdiru.primeservice;

import com.fasterxml.jackson.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@RestController
public class PrimeController {
    @GetMapping("/prime")
    public PrimeResponse getPrimeResponse() {
        var primeResponse = new PrimeResponse(10, List.of(2, 3, 5, 7));
        return primeResponse;
    }
}
