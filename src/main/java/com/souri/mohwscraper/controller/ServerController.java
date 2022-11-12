package com.souri.mohwscraper.controller;

import com.souri.mohwscraper.domain.PlayerDetails;
import com.souri.mohwscraper.domain.Server;
import com.souri.mohwscraper.service.ServerServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Get a list of all available servers along with their statistics e.g. gamemode, map and player count")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping(value = "/servers")
    public ResponseEntity<List<Server>> getServers() {
        return ResponseEntity.ok(serverServiceImpl.getServers());
    }

    //TODO: Differentiate between Battlelog being down and no active servers being found
    @ApiOperation(value = "Get a list of all active servers along with their statistics e.g. gamemode, map and player count")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping(value = "/active-servers")
    public ResponseEntity<List<Server>> getActiveServers() {
        return ResponseEntity.ok(serverServiceImpl.getActiveServers());
    }

    //TODO: Add an endpoint that returns names of players that play on a server

}
