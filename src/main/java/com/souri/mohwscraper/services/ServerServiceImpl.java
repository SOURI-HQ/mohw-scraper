package com.souri.mohwscraper.services;

import com.souri.mohwscraper.domain.Server;
import com.souri.mohwscraper.util.Scraper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class ServerServiceImpl implements ServerService {

    private List<Server> servers = new LinkedList<>();

    private final Scraper scraper;

    public ServerServiceImpl(Scraper scraper) {
        this.scraper = scraper;
    }

    private void loadContents() {
        servers.clear();
        scraper.fetchAllServers().forEach(server -> servers.add(new Server(
                server.get("name"),
                server.get("players"),
                server.get("map"),
                server.get("mode"))));
    }

    public List<Server> getServers() {
        loadContents();
        return servers;
    }

    public List<Server> getActiveServers() {
        loadContents();
        List<Server> activeServers = new LinkedList<>();
        for (Server server : servers) {
            if (server.getPlayerCount().charAt(0) != '0') {
                activeServers.add(server);
            }
        }
        return activeServers;
    }
}
