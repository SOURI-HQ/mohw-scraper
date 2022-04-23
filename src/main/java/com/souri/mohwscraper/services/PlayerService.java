package com.souri.mohwscraper.services;

import com.souri.mohwscraper.domain.player.PlayerDetails;
import com.souri.mohwscraper.domain.player.PlayerOverview;

public interface PlayerService {
    PlayerOverview getPlayerOverview(String playerName);
    PlayerDetails getPlayerDetails(String playerName);
}
