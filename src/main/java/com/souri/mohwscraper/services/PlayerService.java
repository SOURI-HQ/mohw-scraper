package com.souri.mohwscraper.services;

import com.souri.mohwscraper.util.PlayerScraper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlayerService {

    private final PlayerScraper scraper;

    public PlayerService(PlayerScraper scraper) {
        this.scraper = scraper;
    }

//    public String getPlayerID(String playerName) {
//        return scraper.getPlayerURL(playerName);
//    }

    public List<Map<String, String>> getPlayerOverview(String playerName) {
        return scraper.getPlayerDetails(playerName);
    }
}
