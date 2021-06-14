package com.crawler.parser.api;

import com.crawler.parser.dto.Category;
import com.crawler.parser.dto.Source;
import com.crawler.parser.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/source")
public class SourceApi {

    @Autowired
    SourceService sourceService;

    @GetMapping("/verify")
    public ResponseEntity<String> verify() {
        return new ResponseEntity<>("Verifying...", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Source>> getSources() {
        return new ResponseEntity<List<Source>>(sourceService.getSources(), HttpStatus.OK);
    }

    @GetMapping("/category/list")
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<List<Category>>(sourceService.getCategories(), HttpStatus.OK);
    }
}
