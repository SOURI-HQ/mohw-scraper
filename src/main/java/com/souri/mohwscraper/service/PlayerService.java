package com.souri.mohwscraper.service;

import com.souri.mohwscraper.domain.ClassStats;
import com.souri.mohwscraper.domain.PlayerDetails;
import com.souri.mohwscraper.domain.PlayerOverview;

import java.util.List;

public interface PlayerService {
    PlayerOverview getPlayerOverview(String playerName);
    PlayerDetails getPlayerDetails(String playerName);
    List<ClassStats> getClassStats(String playerName);
}
