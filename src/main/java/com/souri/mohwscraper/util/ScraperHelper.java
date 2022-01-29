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
public class ScraperHelper {

    private final String url = "https://battlelog.battlefield.com/mohw/servers/";

    public ScraperHelper() {
    }

    public List<Map<String, String>> fetchAllServers() {

        List<String> html = new ArrayList<>();
        List<Map<String, String>> serversDetails = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Element servers = doc.getElementById("serverguide-listcontainer");
            getAllServerPlayers(servers).forEach(element -> html.add(element.text()));

            for (int i = 0; i < html.size(); i++) {
                int index = i;
                serversDetails.add(new HashMap<>(){{
                    put("name", getAllServerNames(servers).get(index).text());
                    put("players", getAllServerPlayers(servers).get(index).text());
                    put("map", getAllServerMaps(servers).get(index).text());
                    put("mode", getAllServerMode(servers).get(index).text());
                }});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serversDetails;
    }

    public List<Element> getAllServerNames(Element servers) {
        return servers.getElementsByClass("serverguide-cell-name-server-name");
    }

    public List<Element> getAllServerMode(Element servers) {
        return servers.getElementsByClass("serverguide-cell-name-server-info-mode");
    }

    public List<Element> getAllServerMaps(Element servers) {
        return servers.getElementsByClass("serverguide-cell-name-server-info-map");
    }
    public List<Element> getAllServerPlayers(Element servers) {
        return servers.getElementsByClass("serverguide-bodycell serverguide-cell-players");
    }

}
