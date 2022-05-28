package com.souri.mohwscraper.controller;

import com.souri.mohwscraper.domain.ClassStats;
import com.souri.mohwscraper.domain.PlayerDetails;
import com.souri.mohwscraper.domain.PlayerOverview;
import com.souri.mohwscraper.service.PlayerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {

    private final PlayerServiceImpl playerServiceImpl;

    public PlayerController(PlayerServiceImpl playerServiceImpl) {
        this.playerServiceImpl = playerServiceImpl;
    }

    @GetMapping("/player/{name}/overview")
    public ResponseEntity<PlayerOverview> getPlayerOverview(@PathVariable String name){
        return ResponseEntity.ok(playerServiceImpl.getPlayerOverview(name));
    }

    @GetMapping("/player/{name}/details")
    public ResponseEntity<PlayerDetails> getPlayerDetails(@PathVariable String name) {
        return ResponseEntity.ok(playerServiceImpl.getPlayerDetails(name));
    }
    //TODO: Add other endpoints for most essential, standalone stats, like: accuracy, rank, k/d, spm
    //TODO: Add another endpoint for player class stats
    @GetMapping("/player/{name}/classes")
    public ResponseEntity<List<ClassStats>> getClassStats(@PathVariable String name) {
        return ResponseEntity.ok(playerServiceImpl.getClassStats(name));
    }
}
