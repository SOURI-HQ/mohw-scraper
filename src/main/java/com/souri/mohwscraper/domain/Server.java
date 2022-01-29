package com.souri.mohwscraper.domain;

public class Server {

    private String serverName;
    private String playerCount;
    private String map;
    private String gamemode;

    public Server() {
    }

    public Server(String serverName, String playerCount, String map, String gamemode) {
        this.serverName = serverName;
        this.playerCount = playerCount;
        this.map = map;
        this.gamemode = gamemode;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
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
