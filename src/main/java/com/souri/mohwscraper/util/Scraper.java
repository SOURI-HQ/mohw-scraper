package com.souri.mohwscraper.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class Scraper {

    private final String url = "https://battlelog.battlefield.com/mohw/servers/";

    public Scraper() {
    }

    public List<Map<String, String>> fetchAllServers() {

        List<Map<String, String>> serversDetails = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Element servers = doc.getElementById("serverguide-listcontainer");
            if (servers != null) {
                for (int i = 0; i < servers.childrenSize(); i++) {
                    int index = i;
                    serversDetails.add(new HashMap<>() {{
                        put("name", getAllServerNames(servers).get(index).text());
                        put("players", getAllServerPlayers(servers).get(index).text());
                        put("map", getAllServerMaps(servers).get(index).text());
                        put("mode", getAllServerMode(servers).get(index).text());
                    }});
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serversDetails;
    }

    private List<Element> getAllServerNames(Element servers) {
        return servers.getElementsByClass("serverguide-cell-name-server-name");
    }

    private List<Element> getAllServerMode(Element servers) {
        return servers.getElementsByClass("serverguide-cell-name-server-info-mode");
    }

    private List<Element> getAllServerMaps(Element servers) {
        return servers.getElementsByClass("serverguide-cell-name-server-info-map");
    }
    private List<Element> getAllServerPlayers(Element servers) {
        return servers.getElementsByClass("serverguide-bodycell serverguide-cell-players");
    }

}
