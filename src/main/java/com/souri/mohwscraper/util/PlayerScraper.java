package com.souri.mohwscraper.util;

import com.souri.mohwscraper.exception.IncorrectURLException;
import com.souri.mohwscraper.exception.NoSuchWebElementException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class PlayerScraper {

    private String fetchPlayerID(String playerName) {
        String playerID = "";
        try {
            String baseUrl = "https://battlelog.battlefield.com/mohw/en";
            Document doc = Jsoup.connect(baseUrl + "/user/" + playerName).get();
            Element playerAccount = doc.getElementById("soldier-list");
            playerID = playerAccount.children().first().attr("data-id");
            if (playerID.equals("")) {
                throw new NoSuchWebElementException("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchWebElementException e) {
            throw new NoSuchWebElementException("Element not found: Couldn't fetch player's ID");
        } catch (NullPointerException e) {
            throw new IncorrectURLException("Element not found: Couldn't fetch player's ID; check if given parameters are correct e.g player account exists");
        }
        return playerID;
    }

    private JSONObject getJSONResponse(String url) {
        JSONObject response = new JSONObject();
        try {
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            response = new JSONObject(doc.body().text());
        } catch(IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getUrl(String playerName) {
        return "https://battlelog.battlefield.com/mohw/mohwoverviewpopulate/" + fetchPlayerID(playerName) + "/None/1/";
    }

    public Map<String, String> getPlayerOverview(String playerName) {
        String url = getUrl(playerName);

        JSONObject data = getJSONResponse(url).getJSONObject("data");
        JSONObject overviewStats = data.getJSONObject("overviewStats");

        double time = (double) (Integer) overviewStats.get("timePlayed") / 3600;
//        int minutes = (int)((time - Math.floor(time)) * 60);

        return new HashMap<>() {{
            put("score", overviewStats.get("score").toString());
            put("accuracy", overviewStats.get("accuracy").toString());
            put("spm", overviewStats.get("scorePerMinute").toString());
            put("nationsScore", data.getJSONObject("warfighterNation").get("tokensSpent").toString());
            put("winrate", overviewStats.get("winPercentage").toString());
            put("rank", overviewStats.get("rank").toString());
            put("timePlayed", time + "");
        }};
    }

    public Map<String, String> getPlayerDetails(String playerName) {
        String url = getUrl(playerName);

        JSONObject data = getJSONResponse(url).getJSONObject("data");
        JSONObject overviewStats = data.getJSONObject("overviewStats");

        return new HashMap<>() {{
            put("maxKillsInRound", overviewStats.get("maxKillsInRound").toString());
            put("maxMeleeInRound", overviewStats.get("maxMeleeKillsInRound").toString());
            put("meleeKills", overviewStats.get("meleeKills").toString());
            put("maxHeadshotsInRound", overviewStats.get("maxHeadshotsInRound").toString());
            put("kills", overviewStats.get("kills").toString());
            put("deaths", overviewStats.get("deaths").toString());
        }};
    }

    public List<Map<String, String>> getPlayerClasses(String playerName) {
        String url = getUrl(playerName);

        JSONObject data = getJSONResponse(url).getJSONObject("data");
        JSONObject overviewStats = data.getJSONObject("overviewStats");

        String[] classStatsNames = new String[] {"kitTimes", "kitScores",
                "kitKills", "kitDeaths", "kitKillStreak",
                "kitHeadshots", "kitLongestHeadshot", "kitMaxKillsInRound",
                "kitMaxHeadshotsInRound", "kitMaxScoreInRound"};

        List<Map<String, String>> playerClasses = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            playerClasses.add(new HashMap<>());
        }
        for (int i = 0; i < 6; i++) {
            JSONObject stat = overviewStats.getJSONObject("kitTimes");
            Iterator<String> iterator = stat.keys();
            playerClasses.forEach(e -> e.put("type", iterator.next()));
        }
        for (String statName: classStatsNames) {
            JSONObject stat = overviewStats.getJSONObject(statName);
            Iterator<String> iterator = stat.keys();
            playerClasses.forEach(e -> {
                String className = iterator.next();
                e.put(statName, stat.get(className).toString());
            });
            iterator.remove();
        }

        return playerClasses;
    }
}