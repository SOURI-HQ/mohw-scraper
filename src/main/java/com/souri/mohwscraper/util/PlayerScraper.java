package com.souri.mohwscraper.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PlayerScraper {

    private final String url = "https://battlelog.battlefield.com/mohw/en/user/";
    private String playerURL = "https://battlelog.battlefield.com/mohw/en/soldier/SOURI_HQ/stats/1003226078132/pc/";

    private void fetchPlayerURL(String playerName) {
        try {
            Document doc = Jsoup.connect(url + playerName).get();
            Element playerAccount = doc.getElementById("soldier-list");
            if (playerAccount != null && playerAccount.childrenSize() > 0) {
                String playerID = playerAccount.children().first().attr("data-id");
                //playerURL = playerAccount.select()
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
