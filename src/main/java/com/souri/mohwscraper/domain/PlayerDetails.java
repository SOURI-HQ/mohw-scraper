package com.souri.mohwscraper.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "A list of less crucial player's stats")
public class PlayerDetails {

    @ApiModelProperty(value = "highest number of killed players in a round", example = "75")
    private String maxKillsInRound;
    @ApiModelProperty(value = "overall number of killed players using a tomahawk", example = "136")
    private String meleeKills;
    @ApiModelProperty(value = "highest number of headshots in a round", example = "30")
    private String maxHeadshotsInRound;
    @ApiModelProperty(value = "highest number of killed players using a tomahawk in a round", example = "4")
    private String maxMeleeInRound;

    @ApiModelProperty(value = "number of killed players", example = "42386")
    private String kills;
    @ApiModelProperty(value = "number of player's deaths", example = "19074")
    private String deaths;

    public PlayerDetails(String maxKillsInRound, String meleeKills, String maxHeadshotsInRound, String maxMeleeInRound) {
        this.maxKillsInRound = maxKillsInRound;
        this.meleeKills = meleeKills;
        this.maxHeadshotsInRound = maxHeadshotsInRound;
        this.maxMeleeInRound = maxMeleeInRound;
    }

    public PlayerDetails(String maxKillsInRound, String meleeKills, String maxHeadshotsInRound, String maxMeleeInRound, String kills, String deaths) {
        this.maxKillsInRound = maxKillsInRound;
        this.meleeKills = meleeKills;
        this.maxHeadshotsInRound = maxHeadshotsInRound;
        this.maxMeleeInRound = maxMeleeInRound;
        this.kills = kills;
        this.deaths = deaths;
    }

    public String getMaxKillsInRound() {
        return maxKillsInRound;
    }

    public void setMaxKillsInRound(String maxKillsInRound) {
        this.maxKillsInRound = maxKillsInRound;
    }

    public String getMeleeKills() {
        return meleeKills;
    }

    public void setMeleeKills(String meleeKills) {
        this.meleeKills = meleeKills;
    }

    public String getMaxHeadshotsInRound() {
        return maxHeadshotsInRound;
    }

    public void setMaxHeadshotsInRound(String maxHeadshotsInRound) {
        this.maxHeadshotsInRound = maxHeadshotsInRound;
    }

    public String getMaxMeleeInRound() {
        return maxMeleeInRound;
    }

    public void setMaxMeleeInRound(String maxMeleeInRound) {
        this.maxMeleeInRound = maxMeleeInRound;
    }

    public String getKills() {
        return kills;
    }

    public void setKills(String kills) {
        this.kills = kills;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }
}
