package com.souri.mohwscraper.service;

import com.souri.mohwscraper.domain.PlayerClasses;
import com.souri.mohwscraper.domain.PlayerDetails;
import com.souri.mohwscraper.domain.PlayerOverview;

import java.util.List;

public interface PlayerService {
    PlayerOverview getPlayerOverview(String playerName);
    PlayerDetails getPlayerDetails(String playerName);
    List<PlayerClasses> getPlayerClasses(String playerName);
}
