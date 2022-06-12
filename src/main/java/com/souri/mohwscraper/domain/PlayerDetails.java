package com.souri.mohwscraper.domain;

public class PlayerDetails {

    private String maxKillsInRound;
    private String meleeKills;
    private String maxHeadshotsInRound;
    private String maxMeleeInRound;

    private String kills;
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
