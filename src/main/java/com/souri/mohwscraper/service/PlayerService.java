package com.souri.mohwscraper.service;

import com.souri.mohwscraper.domain.PlayerDetails;
import com.souri.mohwscraper.domain.PlayerOverview;

public interface PlayerService {
    PlayerOverview getPlayerOverview(String playerName);
    PlayerDetails getPlayerDetails(String playerName);
}
