package com.souri.mohwscraper.domain;

public class PlayerClasses {
    private String classType;
    private String kills;
    private String deaths;
    private String score;
    private String time;
    private String killStreak;
    private String headshots;
    private String longestHeadshot;
    private String headshotsInRound;
    private String maxScoreInRound;
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
