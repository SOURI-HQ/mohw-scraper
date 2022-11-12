package com.souri.mohwscraper.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "A list of player's stats per class types e.g. Assaulter, Spec Ops")
public class PlayerClasses {

    @ApiModelProperty(value = "class type", example = "Assaulter")
    private String classType;
    @ApiModelProperty(value = "number of killed players", example = "15675")
    private String kills;
    @ApiModelProperty(value = "number of player's deaths", example = "7037")
    private String deaths;
    @ApiModelProperty(value = "overall score", example = "3218480")
    private String score;
    @ApiModelProperty(value = "time spent playing the class in hours", example = "113.93777777777778")
    private String time;
    @ApiModelProperty(value = "highest number of consecutively killed players", example = "22")
    private String killStreak;
    @ApiModelProperty(value = "number of headshots", example = "2778")
    private String headshots;
    @ApiModelProperty(value = "longest headshots distance", example = "346")
    private String longestHeadshot;
    @ApiModelProperty(value = "highest number of headshots in a round", example = "14")
    private String headshotsInRound;
    @ApiModelProperty(value = "highest score in a round", example = "12137")
    private String maxScoreInRound;
    @ApiModelProperty(value = "highest number of kills in round", example = "65")
    private String maxKillsInRound;

    public PlayerClasses(String classType, String kills, String deaths, String score, String time,
                         String killStreak, String headshots, String longestHeadshot,
                         String headshotsInRound, String maxScoreInRound, String maxKillsInRound) {
        this.classType = classType;
        this.kills = kills;
        this.deaths = deaths;
        this.score = score;
        this.time = time;
        this.killStreak = killStreak;
        this.headshots = headshots;
        this.longestHeadshot = longestHeadshot;
        this.headshotsInRound = headshotsInRound;
        this.maxScoreInRound = maxScoreInRound;
        this.maxKillsInRound = maxKillsInRound;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKillStreak() {
        return killStreak;
    }

    public void setKillStreak(String killStreak) {
        this.killStreak = killStreak;
    }

    public String getHeadshots() {
        return headshots;
    }

    public void setHeadshots(String headshots) {
        this.headshots = headshots;
    }

    public String getLongestHeadshot() {
        return longestHeadshot;
    }

    public void setLongestHeadshot(String longestHeadshot) {
        this.longestHeadshot = longestHeadshot;
    }

    public String getHeadshotsInRound() {
        return headshotsInRound;
    }

    public void setHeadshotsInRound(String headshotsInRound) {
        this.headshotsInRound = headshotsInRound;
    }

    public String getMaxScoreInRound() {
        return maxScoreInRound;
    }

    public void setMaxScoreInRound(String maxScoreInRound) {
        this.maxScoreInRound = maxScoreInRound;
    }

    public String getMaxKillsInRound() {
        return maxKillsInRound;
    }

    public void setMaxKillsInRound(String maxKillsInRound) {
        this.maxKillsInRound = maxKillsInRound;
    }
}
