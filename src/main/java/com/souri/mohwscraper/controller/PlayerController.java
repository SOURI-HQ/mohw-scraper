package com.souri.mohwscraper.controller;

import com.souri.mohwscraper.domain.PlayerClasses;
import com.souri.mohwscraper.domain.PlayerDetails;
import com.souri.mohwscraper.domain.PlayerOverview;
import com.souri.mohwscraper.service.PlayerServiceImpl;
import com.souri.mohwscraper.service.PlayerServiceImplV1;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {

    private final PlayerServiceImpl playerServiceImpl;
    private final PlayerServiceImplV1 playerServiceImplV1;

    public PlayerController(PlayerServiceImpl playerServiceImpl, PlayerServiceImplV1 playerServiceImplV1) {
        this.playerServiceImpl = playerServiceImpl;
        this.playerServiceImplV1 = playerServiceImplV1;
    }

    //TODO: Add endpoints with already formatted values (e.g. instead "accuracy": "29.86439946952959" -> "29.9%")
    @GetMapping("/player/{name}/overview")
    public ResponseEntity<PlayerOverview> getPlayerOverview(@PathVariable String name) {
        return ResponseEntity.ok(playerServiceImpl.getPlayerOverview(name));
    }

    @GetMapping(value = "/player/{name}/details")
    public ResponseEntity<PlayerDetails> getPlayerDetails(@PathVariable String name) {
        return ResponseEntity.ok(playerServiceImpl.getPlayerDetails(name));
    }

    @GetMapping(value = "/player/{name}/classes")
    public ResponseEntity<List<PlayerClasses>> getClassStats(@PathVariable String name) {
        return ResponseEntity.ok(playerServiceImpl.getPlayerClasses(name));
    }

//    First version of the scraper using selenium web driver to get data
    @GetMapping(value = "/player/{name}/overview", params = "version=1")
    public ResponseEntity<PlayerOverview> getPlayerOverview_v1(@PathVariable String name) {
        return ResponseEntity.ok(playerServiceImplV1.getPlayerOverview(name));
    }

    @GetMapping(value = "/player/{name}/details", params = "version=1")
    public ResponseEntity<PlayerDetails> getPlayerDetails_v1(@PathVariable String name) {
        return ResponseEntity.ok(playerServiceImplV1.getPlayerDetails(name));
    }

    @GetMapping(value = "/player/{name}/classes", params = "version=1")
    public ResponseEntity<List<PlayerClasses>> getClassStats_v1(@PathVariable String name) {
        return ResponseEntity.ok(playerServiceImplV1.getPlayerClasses(name));
    }
    //TODO: Add other endpoints for most essential, standalone stats, like: accuracy, rank, k/d, spm
}
