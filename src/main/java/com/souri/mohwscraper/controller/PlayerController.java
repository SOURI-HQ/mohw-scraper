package com.souri.mohwscraper.controller;

import com.souri.mohwscraper.domain.PlayerClasses;
import com.souri.mohwscraper.domain.PlayerDetails;
import com.souri.mohwscraper.domain.PlayerOverview;
import com.souri.mohwscraper.service.PlayerServiceImpl;
import com.souri.mohwscraper.service.PlayerServiceImplV1;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
public class PlayerController {

    private final PlayerServiceImpl playerServiceImpl;
    private final PlayerServiceImplV1 playerServiceImplV1;

    public PlayerController(PlayerServiceImpl playerServiceImpl, PlayerServiceImplV1 playerServiceImplV1) {
        this.playerServiceImpl = playerServiceImpl;
        this.playerServiceImplV1 = playerServiceImplV1;
    }

    @ApiOperation(value = "Get an overview of player's most generic stats e.g. rank, accuracy, score per minute (spm)", notes = "Need to provide existing player's name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PlayerOverview.class),
            @ApiResponse(code = 400, message = "Player not found on Battlelog"),
            @ApiResponse(code = 503, message = "Battlelog platform could not provide requested service")
    })
    @GetMapping("/player/{name}/overview")
    public ResponseEntity<PlayerOverview> getPlayerOverview(@ApiParam(value = "Player's name", example = "SOURI_HQ") @PathVariable String name) {
        return ResponseEntity.ok(playerServiceImpl.getPlayerOverview(name));
    }


    @ApiOperation(value = "Get a list of secondary player's stats e.g. max kills in a round or overall number of melee kills", notes = "Need to provide existing player's name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PlayerDetails.class),
            @ApiResponse(code = 400, message = "Player not found on Battlelog"),
            @ApiResponse(code = 503, message = "Battlelog platform could not provide requested service")
    })
    @GetMapping(value = "/player/{name}/details")
    public ResponseEntity<PlayerDetails> getPlayerDetails(@ApiParam(value = "Player's name", example = "SOURI_HQ") @PathVariable String name) {
        return ResponseEntity.ok(playerServiceImpl.getPlayerDetails(name));
    }


    @ApiOperation(value = "Get player's list of class types along with corresponding to them specific stats like kills, deaths and headshots", notes = "Need to provide existing player's name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PlayerClasses.class),
            @ApiResponse(code = 400, message = "Player not found on Battlelog"),
            @ApiResponse(code = 503, message = "Battlelog platform could not provide requested service")
    })
    @GetMapping(value = "/player/{name}/classes")
    public ResponseEntity<List<PlayerClasses>> getClassStats(@ApiParam(value = "Player's name", example = "SOURI_HQ") @PathVariable String name) {
        return ResponseEntity.ok(playerServiceImpl.getPlayerClasses(name));
    }

//    First version of the scraper using selenium web driver to get data

    @ApiIgnore
    @GetMapping(value = "/player/{name}/overview", params = "version=1")
    public ResponseEntity<PlayerOverview> getPlayerOverview_v1(@PathVariable String name) {
        return ResponseEntity.ok(playerServiceImplV1.getPlayerOverview(name));
    }

    @ApiIgnore
    @GetMapping(value = "/player/{name}/details", params = "version=1")
    public ResponseEntity<PlayerDetails> getPlayerDetails_v1(@PathVariable String name) {
        return ResponseEntity.ok(playerServiceImplV1.getPlayerDetails(name));
    }

    @ApiIgnore
    @GetMapping(value = "/player/{name}/classes", params = "version=1")
    public ResponseEntity<List<PlayerClasses>> getClassStats_v1(@PathVariable String name) {
        return ResponseEntity.ok(playerServiceImplV1.getPlayerClasses(name));
    }
}
