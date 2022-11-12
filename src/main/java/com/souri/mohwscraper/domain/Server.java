package com.souri.mohwscraper.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "A list of stats describing current server's status")
public class Server {

    @ApiModelProperty(value = "server's name", example = "EA Official - NFOservers.com - Dallas # 01")
    private String name;
    @ApiModelProperty(value = "current server's population ratio", example = "5 / 20")
    private String playerCount;
    @ApiModelProperty(value = "currently played map", example = "Sarajevo Stadium")
    private String map;
    @ApiModelProperty(value = "current played gamemode", example = "Combat Mission")
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
