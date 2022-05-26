package com.souri.mohwscraper.domain;

public class PlayerOverview {

    private String score;
    private String accuracy;
    private String spm;
    private String nationsScore;
    private String winrate;
    private String rank;
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
