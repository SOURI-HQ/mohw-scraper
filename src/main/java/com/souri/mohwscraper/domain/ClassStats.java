package com.souri.mohwscraper.domain;

public class ClassStats {
    private String classType;
    private String kills;
    private String deaths;
    private String score;
    private String time;
    private String killStreak;
    private String headshots;
    private String longestHeadshot;
    private String headshotInARound;
    private String maxScoreInARound;
    private String maxKillsInARound;

    public ClassStats() {
    }

    public ClassStats(String classType, String kills, String deaths, String score, String time,
                      String killStreak, String headshots, String longestHeadshot,
                      String headshotInARound, String maxScoreInARound, String maxKillsInARound) {
        this.classType = classType;
        this.kills = kills;
        this.deaths = deaths;
        this.score = score;
        this.time = time;
        this.killStreak = killStreak;
        this.headshots = headshots;
        this.longestHeadshot = longestHeadshot;
        this.headshotInARound = headshotInARound;
        this.maxScoreInARound = maxScoreInARound;
        this.maxKillsInARound = maxKillsInARound;
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

    public String getHeadshotInARound() {
        return headshotInARound;
    }

    public void setHeadshotInARound(String headshotInARound) {
        this.headshotInARound = headshotInARound;
    }

    public String getMaxScoreInARound() {
        return maxScoreInARound;
    }

    public void setMaxScoreInARound(String maxScoreInARound) {
        this.maxScoreInARound = maxScoreInARound;
    }

    public String getMaxKillsInARound() {
        return maxKillsInARound;
    }

    public void setMaxKillsInARound(String maxKillsInARound) {
        this.maxKillsInARound = maxKillsInARound;
    }
}
