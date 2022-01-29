package com.souri.mohwscraper.services;

import com.souri.mohwscraper.domain.Server;
import com.souri.mohwscraper.util.ScraperHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {

    private List<Server> servers = new ArrayList<>();

    private final ScraperHelper scraperHelper;

    public ScraperService(ScraperHelper scraperHelper) {
        this.scraperHelper = scraperHelper;
    }

    public void loadContents() {
        scraperHelper.fetchAllServers().forEach(server -> servers.add(new Server(
                server.get("name"),
                server.get("players"),
                server.get("map"),
                server.get("mode"))));
    }

    public List<Server> getServersDetails() {
        loadContents();
        return servers;
    }
}
