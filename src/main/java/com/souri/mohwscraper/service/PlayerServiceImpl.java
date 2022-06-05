package com.souri.mohwscraper.service;

import com.souri.mohwscraper.domain.PlayerClasses;
import com.souri.mohwscraper.domain.PlayerDetails;
import com.souri.mohwscraper.domain.PlayerOverview;
import com.souri.mohwscraper.util.PlayerScraper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerScraper scraper;

    public PlayerServiceImpl(PlayerScraper scraper) {
        this.scraper = scraper;
    }

    public PlayerOverview getPlayerOverview(String playerName) {
        return null;
    }
    public PlayerDetails getPlayerDetails(String playerName) {
        return null;
    }
    public List<PlayerClasses> getPlayerClasses(String playerName) {
        return null;
    }
}
