package com.souri.mohwscraper.service;

import com.souri.mohwscraper.domain.PlayerClasses;
import com.souri.mohwscraper.domain.PlayerDetails;
import com.souri.mohwscraper.domain.PlayerOverview;
import com.souri.mohwscraper.util.PlayerScraper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<PlayerClasses> getPlayerClasses(String playerName) {
        List<Map<String, String>> classStats = scraper.getClassStats(playerName);

        return new ArrayList<>() {{
            for (int i = 0; i < classStats.size(); i++) {
                add(new PlayerClasses(classStats.get(i).get("Class"),
                        classStats.get(i).get("Kills"),
                        classStats.get(i).get("Deaths"),
                        classStats.get(i).get("Score"),
                        classStats.get(i).get("Time"),
                        classStats.get(i).get("Kill streak"),
                        classStats.get(i).get("Headshots"),
                        classStats.get(i).get("Longest Headshot"),
                        classStats.get(i).get("Headshots in a round"),
                        classStats.get(i).get("Max score in a round"),
                        classStats.get(i).get("Max kills in a round")));
            }
        }};
    }
}
