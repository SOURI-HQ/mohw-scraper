package com.souri.mohwscraper.domain;

public class Server {

    private String server;
    private String playerCount;
    private String map;
    private String gamemode;

    public Server() {
    }

    public Server(String server, String playerCount, String map, String gamemode) {
        this.server = server;
        this.playerCount = playerCount;
        this.map = map;
        this.gamemode = gamemode;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(String playerCount) {
        this.playerCount = playerCount;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getGamemode() {
        return gamemode;
    }

    public void setGamemode(String gamemode) {
        this.gamemode = gamemode;
    }
}
