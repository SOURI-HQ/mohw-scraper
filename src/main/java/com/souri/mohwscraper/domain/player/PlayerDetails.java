package com.souri.mohwscraper.domain.player;

public class PlayerDetails {

    private String maxKillsInRound;
    private String meleeKills;
    private String maxHeadshotsInRound;
    private String maxMeleeInRound;

    public PlayerDetails() {
    }

    public PlayerDetails(String maxKillsInRound, String meleeKills, String maxHeadshotsInRound, String maxMeleeInRound) {
        this.maxKillsInRound = maxKillsInRound;
        this.meleeKills = meleeKills;
        this.maxHeadshotsInRound = maxHeadshotsInRound;
        this.maxMeleeInRound = maxMeleeInRound;
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
}
