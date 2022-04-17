package com.souri.mohwscraper.services;

import com.souri.mohwscraper.domain.player.PlayerDetails;
import com.souri.mohwscraper.domain.player.PlayerOverview;
import com.souri.mohwscraper.exceptions.IncorrectURLException;

public interface PlayerService {
    PlayerOverview getPlayerOverview(String playerName) throws IncorrectURLException;
    PlayerDetails getPlayerDetails(String playerName) throws IncorrectURLException;
}
