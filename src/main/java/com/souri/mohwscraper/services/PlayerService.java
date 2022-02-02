package com.souri.mohwscraper.services;

import com.souri.mohwscraper.util.PlayerScraper;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerScraper scraper;

    public PlayerService(PlayerScraper scraper) {
        this.scraper = scraper;
    }

    public String getPlayerID(String playerName) {
        return scraper.getPlayerURL(playerName);
    }
}
