package com.souri.mohwscraper.domain;

public class Server {

    private String name;
    private String playerCount;
    private String map;
    private String gamemode;

    public Server(String name, String playerCount, String map, String gamemode) {
        this.name = name;
        this.playerCount = playerCount;
        this.map = map;
        this.gamemode = gamemode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
