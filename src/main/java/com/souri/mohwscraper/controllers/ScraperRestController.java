package com.souri.mohwscraper.controllers;

import com.souri.mohwscraper.domain.Server;
import com.souri.mohwscraper.services.ScraperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScraperRestController {

    private final ScraperService scraperService;

    public ScraperRestController(ScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @GetMapping("/test")
    public String getTestMessage() {
        return "test";
    }

    @GetMapping("/servers")
    public ResponseEntity<List<Server>> getServers() {
        return ResponseEntity.ok(scraperService.getServersDetails());
    }
}
