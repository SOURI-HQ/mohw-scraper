package com.souri.mohwscraper.controller;

import com.souri.mohwscraper.domain.Server;
import com.souri.mohwscraper.services.ServerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServerController {

    private final ServerServiceImpl serverServiceImpl;

    public ServerController(ServerServiceImpl serverServiceImpl) {
        this.serverServiceImpl = serverServiceImpl;
    }

    @GetMapping(value = "/servers")
    public ResponseEntity<List<Server>> getServers() {
        return ResponseEntity.ok(serverServiceImpl.getServers());
    }

    @GetMapping(value = "/active-servers")
    public ResponseEntity<List<Server>> getActiveServers() {
        return ResponseEntity.ok(serverServiceImpl.getActiveServers());
    }

}
