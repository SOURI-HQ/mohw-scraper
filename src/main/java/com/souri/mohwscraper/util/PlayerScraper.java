package com.souri.mohwscraper.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PlayerScraper {

    private final String url = "https://battlelog.battlefield.com/mohw/en/user/";
    private String playerURL = "";

    private void fetchPlayerURL(String playerName) {
        try {
            Document doc = Jsoup.connect(url + playerName).get();
            Element playerAccount = doc.getElementById("soldier-list").children().first();
            if (playerAccount != null) {
                String playerID = playerAccount.attr("data-id");
                playerURL = url + playerName + "/" + playerID;
                System.out.println(playerURL);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String getPlayerURL(String playerName) {
        fetchPlayerURL(playerName);
        return playerURL;
    }
}
