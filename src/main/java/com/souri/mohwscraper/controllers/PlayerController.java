package com.souri.mohwscraper.controllers;

import com.souri.mohwscraper.services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

//    @GetMapping("/player/{name}")
//    public String getPlayerID(@PathVariable String name) {
//        return playerService.getPlayerID(name);
//    }

    @GetMapping("/player/{name}/overview")
    public ResponseEntity<List<Map<String, String>>> getPlayerOverview(@PathVariable String name) {
        return ResponseEntity.ok(playerService.getPlayerOverview(name));
    }
}
