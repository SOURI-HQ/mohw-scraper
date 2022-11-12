package com.souri.mohwscraper.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "A list of player's most general stats")
public class PlayerOverview {

    @ApiModelProperty(value = "overall player's score", example = "12915500")
    private String score;
    @ApiModelProperty(value = "accuracy percentage", example = "29.865059417778973")
    private String accuracy;
    @ApiModelProperty(value = "average score per minute", example = "471.3534952900347")
    private String spm;
    @ApiModelProperty(value = "warfighter nations score", example = "6051")
    private String nationsScore;
    @ApiModelProperty(value = "percentage of games won", example = "50.9375")
    private String winrate;
    @ApiModelProperty(value = "rank", example = "106")
    private String rank;
    @ApiModelProperty(value = "overall time played in hours", example = "302.55")
    private String timePlayed;

    public PlayerOverview(String score, String accuracy, String spm, String nationsScore, String winrate, String rank, String timePlayed) {
        this.score = score;
        this.accuracy = accuracy;
        this.spm = spm;
        this.nationsScore = nationsScore;
        this.winrate = winrate;
        this.rank = rank;
        this.timePlayed = timePlayed;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getSpm() {
        return spm;
    }

    public void setSpm(String spm) {
        this.spm = spm;
    }

    public String getNationsScore() {
        return nationsScore;
    }

    public void setNationsScore(String nationsScore) {
        this.nationsScore = nationsScore;
    }

    public String getWinrate() {
        return winrate;
    }

    public void setWinrate(String winrate) {
        this.winrate = winrate;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(String timePlayed) {
        this.timePlayed = timePlayed;
    }
}
