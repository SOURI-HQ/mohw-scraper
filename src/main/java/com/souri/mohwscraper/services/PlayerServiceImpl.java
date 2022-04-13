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
        List<Map<String, String>> playerDetails = scraper.getPlayerDetails(playerName);
        if (playerDetails.isEmpty()) {
            return new PlayerDetails();
        }
        else {
            return new PlayerDetails(playerDetails.get(0).get("Max kills in a round"),
                    playerDetails.get(0).get("Melee kills"),
                    playerDetails.get(0).get("Max headshots in a round"),
                    playerDetails.get(0).get("Max melee kills in a round"));
        }
    }
}
