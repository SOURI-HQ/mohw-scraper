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
public class ServerScraper {

    private final String url = "https://battlelog.battlefield.com/mohw/servers/";

    public ServerScraper() {
    }

    public List<Map<String, String>> fetchAllServers() {

        List<Map<String, String>> serversDetails = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Element servers = doc.getElementById("serverguide-listcontainer");
            //TODO: is there a better practice for checking if fetching DOM elements returns null
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
