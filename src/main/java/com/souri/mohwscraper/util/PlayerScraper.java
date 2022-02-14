package com.souri.mohwscraper.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PlayerScraper {

    private final String baseUrl = "https://battlelog.battlefield.com/mohw/en";

    private String fetchPlayerID(String playerName) {
        try {
            Document doc = Jsoup.connect(baseUrl + "/user/" + playerName).get();
            Element playerAccount = doc.getElementById("soldier-list");
            if (playerAccount != null && playerAccount.childrenSize() > 0) {
                String playerID = playerAccount.children().first().attr("data-id");
                //String playerURL = url + "/soldier/" + playerName + "/stats/" + playerID + "/pc";
                return playerID;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public Element getPlayerURL(String playerName) {
//        Document docPlayerProf = fetchPlayerURL(playerName);
//        return new Element(docPlayerProf.baseUri());
//    }

    public List<Map<String, String>> getPlayerDetails(String playerName) {
        String playerID = fetchPlayerID(playerName);
        List<Map<String, String>> playerOverview = new ArrayList<>();
        try {
            //TODO: figure out how to get ALL content once js loads it in
            Document doc = Jsoup.connect(baseUrl + "/soldier/" + playerName + "/stats/" + playerID + "/pc").get();
            List<Element> playerDetails = doc.getElementById("overview-numbers").children();

            playerOverview.add(new HashMap<>() {{
                put("rank", doc.getElementById("overview-rank-number").text());
                playerDetails.forEach(n -> {
                    put(n.getElementsByTag("h4").text(), n.getElementsByTag("p").text());
                });
            }});
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return playerOverview;
    }
}
