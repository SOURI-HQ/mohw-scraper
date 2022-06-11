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

    private enum ClassType {
        c_1024("Spec Ops"),
        c_512("Pointman"),
        c_256("Assaulter"),
        c_128("Demolition"),
        c_32("Heavy Gunner"),
        c_8("Sniper");

        private final String returnedTypeName;

        ClassType(String returnedTypeName) {
            this.returnedTypeName = returnedTypeName;
        }
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
        List<Map<String, String>> playerClasses = scraper.getPlayerClasses(playerName);
        List<PlayerClasses> playerClassesList = new ArrayList<>() {{
            for (int i = 0; i < playerClasses.size(); i++) {
                add(new PlayerClasses(playerClasses.get(i).get("type"),
                        playerClasses.get(i).get("kitKills"),
                        playerClasses.get(i).get("kitDeaths"),
                        playerClasses.get(i).get("kitScores"),
                        playerClasses.get(i).get("kitTimes"),
                        playerClasses.get(i).get("kitKillStreak"),
                        playerClasses.get(i).get("kitHeadshots"),
                        playerClasses.get(i).get("kitLongestHeadshot"),
                        playerClasses.get(i).get("kitMaxHeadshotsInRound"),
                        playerClasses.get(i).get("kitMaxScoreInRound"),
                        playerClasses.get(i).get("kitMaxKillsInRound"))
                );
            }
        }};

        playerClassesList.forEach(e -> {
            double time = (double) Integer.parseInt(e.getTime()) / 3600;
            e.setTime(time + "");

            switch(e.getClassType()) {
                case "1024":
                    e.setClassType(ClassType.c_1024.returnedTypeName);
                    break;
                case "512":
                    e.setClassType(ClassType.c_512.returnedTypeName);
                    break;
                case "256":
                    e.setClassType(ClassType.c_256.returnedTypeName);
                    break;
                case "128":
                    e.setClassType(ClassType.c_128.returnedTypeName);
                    break;
                case "32":
                    e.setClassType(ClassType.c_32.returnedTypeName);
                    break;
                case "8":
                    e.setClassType(ClassType.c_8.returnedTypeName);
                    break;
            }
        });

        return playerClassesList;
    }
}
