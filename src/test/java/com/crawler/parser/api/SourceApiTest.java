package com.crawler.parser.api;

import com.crawler.parser.ParserApplication;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SourceApiTests {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void testVerify() {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/source/verify"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                });
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testgetSources() {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/source/list"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                });
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetCategories() {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/source/category/list"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                });
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
