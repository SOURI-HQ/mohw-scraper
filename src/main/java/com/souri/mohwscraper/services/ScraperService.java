package com.souri.mohwscraper.services;

import com.souri.mohwscraper.domain.Server;
import com.souri.mohwscraper.util.Scraper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {

    private List<Server> servers = new ArrayList<>();

    private final Scraper scraper;

    public ScraperService(Scraper scraper) {
        this.scraper = scraper;
    }

    private void loadContents() {
        scraper.fetchAllServers().forEach(server -> servers.add(new Server(
                server.get("name"),
                server.get("players"),
                server.get("map"),
                server.get("mode"))));
    }

    public List<Server> getServersDetails() {
        loadContents();
        System.out.println();
        return servers;
    }
}
