package com.souri.mohwscraper.service;

import com.souri.mohwscraper.domain.Server;
import com.souri.mohwscraper.util.ServerScraper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class ServerServiceImpl implements ServerService {

    private final ServerScraper scraper;

    public ServerServiceImpl(ServerScraper scraper) {
        this.scraper = scraper;
    }

    private List<Server> loadContents() {
        List<Server> servers = new LinkedList<>();
        scraper.fetchAllServers().forEach(server -> servers.add(new Server(
                server.get("name"),
                server.get("players"),
                server.get("map"),
                server.get("mode"))));
        return servers;
    }

    public List<Server> getServers() {
        return loadContents();
    }

    public List<Server> getActiveServers() {
        List<Server> servers = loadContents();
        List<Server> activeServers = new LinkedList<>();
        for (Server server : servers) {
            if (server.getPlayerCount().charAt(0) != '0') {
                activeServers.add(server);
            }
        }
        return activeServers;
    }
}
