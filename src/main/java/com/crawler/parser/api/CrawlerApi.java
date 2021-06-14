package com.crawler.parser.api;

import com.crawler.parser.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CrawlerApi {

    @Autowired
    private CrawlerService crawlerService;

    @GetMapping("/version")
    public ResponseEntity<String> getVersion() {
        return new ResponseEntity<>("0.1.1", HttpStatus.OK);
    }

    @GetMapping("/start")
    public ResponseEntity<Map>  start() {
        return new ResponseEntity<Map>(crawlerService.run(), HttpStatus.OK);
    }

    @GetMapping("/stop")
    public ResponseEntity<String>  stop() {
        return new ResponseEntity<>("Stopped", HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<String>  getStatus() {
        return new ResponseEntity<>("Running...", HttpStatus.OK);
    }
}
