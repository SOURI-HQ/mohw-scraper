package com.souri.mohwscraper.services;

import com.souri.mohwscraper.domain.player.PlayerDetails;
import com.souri.mohwscraper.domain.player.PlayerOverview;
import com.souri.mohwscraper.util.PlayerScraper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerScraper scraper;

    public PlayerServiceImpl(PlayerScraper scraper) {
        this.scraper = scraper;
    }

//    public String getPlayerID(String playerName) {
//        return scraper.getPlayerURL(playerName);
//    }

    public PlayerOverview getPlayerOverview(String playerName) {
        Map<String, String> playerOverview = scraper.getPlayerOverview(playerName);

        return new PlayerOverview(playerOverview.get("Score"),
                playerOverview.get("Accuracy"),
                playerOverview.get("Score / Min"),
                playerOverview.get("Warfighter Nations score"),
                playerOverview.get("Win percentage"),
                playerOverview.get("Rank"),
                playerOverview.get("Time played"));
    }

    public PlayerDetails getPlayerDetails(String playerName) {
        Map<String, String> playerDetails = scraper.getPlayerDetails(playerName);

        return new PlayerDetails(playerDetails.get("Max kills in a round"),
                playerDetails.get("Melee kills"),
                playerDetails.get("Max headshots in a round"),
                playerDetails.get("Max melee kills in a round"));
    }
}
