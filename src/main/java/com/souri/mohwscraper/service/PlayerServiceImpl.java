package com.souri.mohwscraper.service;

import com.souri.mohwscraper.domain.PlayerClasses;
import com.souri.mohwscraper.domain.PlayerDetails;
import com.souri.mohwscraper.domain.PlayerOverview;
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

    public PlayerOverview getPlayerOverview(String playerName) {
        Map<String, String> playerOverview = scraper.getPlayerOverview(playerName);

        return new PlayerOverview(playerOverview.get("score"),
                playerOverview.get("accuracy"),
                playerOverview.get("spm"),
                playerOverview.get("nationsScore"),
                playerOverview.get("winrate"),
                playerOverview.get("rank"),
                playerOverview.get("timePlayed")
        );
    }
    public PlayerDetails getPlayerDetails(String playerName) {
        Map<String, String> playerDetails = scraper.getPlayerDetails(playerName);

        return new PlayerDetails(
                playerDetails.get("maxKillsInRound"),
                playerDetails.get("meleeKills"),
                playerDetails.get("maxHeadshotsInRound"),
                playerDetails.get("maxMeleeInRound"),
                playerDetails.get("kills"),
                playerDetails.get("deaths")
        );
    }
    public List<PlayerClasses> getPlayerClasses(String playerName) {
        return null;
    }
}
