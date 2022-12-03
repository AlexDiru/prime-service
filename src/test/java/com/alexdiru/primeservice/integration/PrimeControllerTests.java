package com.alexdiru.primeservice.integration;

import com.alexdiru.primeservice.PrimeServiceApplication;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PrimeServiceApplication.class
)
public class PrimeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource(value = "validJsonTestCaseData")
    public void whenInputIsValidAndRequestedJson_shouldRespondJson(ValidTestCase validTestCase) throws Exception {
        MockHttpServletRequestBuilder getRequest = get(validTestCase.url).accept(validTestCase.accept);

        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().contentType(validTestCase.accept))
                .andExpect(content().json(validTestCase.expectedResponse));
    }

    @ParameterizedTest
    @MethodSource(value = "validXmlTestCaseData")
    public void whenInputIsValidAndRequestedXml_shouldRespondXml(ValidTestCase validTestCase) throws Exception {
        MockHttpServletRequestBuilder getRequest = get(validTestCase.url).accept(validTestCase.accept);

        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().contentType(validTestCase.accept))
                .andExpect(content().xml(validTestCase.expectedResponse));
    }

    @ParameterizedTest
    @MethodSource(value = "invalidTestCaseData")
    public void whenInputIsInvalid_shouldDisplayError(InvalidTestCase invalidTestCase) throws Exception {
        MockHttpServletRequestBuilder getRequest = get(invalidTestCase.url).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(getRequest)
                .andExpect(status().is4xxClientError());
    }

    public static List<ValidTestCase> validJsonTestCaseData() {
        return List.of(
                new ValidTestCase("/primes/12", MediaType.APPLICATION_JSON, "{\"initial\":12,\"primes\":[2,3,5,7,11]}"),
                new ValidTestCase("/primes/13", MediaType.APPLICATION_JSON, "{\"initial\":13,\"primes\":[2,3,5,7,11,13]}")
        );
    }

    public static List<ValidTestCase> validXmlTestCaseData() {
        return List.of(
                new ValidTestCase("/primes/12", MediaType.APPLICATION_XML, "<PrimeResponse><initial>12</initial><primes><primes>2</primes><primes>3</primes><primes>5</primes><primes>7</primes><primes>11</primes></primes></PrimeResponse>"),
                new ValidTestCase("/primes/13", MediaType.APPLICATION_XML, "<PrimeResponse><initial>13</initial><primes><primes>2</primes><primes>3</primes><primes>5</primes><primes>7</primes><primes>11</primes><primes>13</primes></primes></PrimeResponse>")
        );
    }

    public static List<InvalidTestCase> invalidTestCaseData() {
        return List.of(
                new InvalidTestCase("/primes/x"),
                new InvalidTestCase("/primes/3.5"));
    }

    private static class InvalidTestCase {
        private String url;

        public InvalidTestCase(String url) {
            this.url = url;
        }
    }

    private static class ValidTestCase {
        private String url;
        private MediaType accept;
        private String expectedResponse;

        public ValidTestCase(String url, MediaType accept, String expectedResponse) {
            this.url = url;
            this.accept = accept;
            this.expectedResponse = expectedResponse;
        }

        public String getUrl() {
            return url;
        }

        public MediaType getAccept() {
            return accept;
        }

        public String getExpectedResponse() {
            return expectedResponse;
        }
    }
}
