package com.souri.mohwscraper;

import com.souri.mohwscraper.domain.player.PlayerDetails;
import com.souri.mohwscraper.domain.player.PlayerOverview;
import com.souri.mohwscraper.services.PlayerServiceImpl;
import com.souri.mohwscraper.util.PlayerScraper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PlayerServiceIT {

    @Autowired
    private PlayerServiceImpl playerService;

    @MockBean
    private PlayerScraper playerScraper;

    @Test
    void shouldReturnNotNullPlayerOverview() {
        Map<String, String> mockedPlayerOverviewStats = new HashMap<>()
        {{
            put("Score", "1");
            put("Accuracy", "1");
            put("Score / Min", "1");
            put("Warfighter Nations score", "1");
            put("Win percentage", "1");
            put("Rank", "1");
            put("Time played", "1");
        }};
        when(playerScraper.getPlayerOverview(any())).thenReturn(mockedPlayerOverviewStats);
        assertThat(playerService.getPlayerOverview("SOURI_HQ")).isNotNull();
    }

    @Test
    void shouldReturnNotNullPlayerDetails() {
        Map<String, String> mockedPlayerDetailsStats = new HashMap<>()
        {{
            put("Max kills in a round", "1");
            put("Melee kills", "1");
            put("Max headshots in a round", "1");
            put("Max melee kills in a round", "1");
        }};
        when(playerScraper.getPlayerDetails(any())).thenReturn(mockedPlayerDetailsStats);
        assertThat(playerService.getPlayerDetails("SOURI_HQ")).isNotNull();
    }

    @Test
    void shouldReturnNotNullPlayerOverviewFields() {
        Map<String, String> mockedPlayerOverviewStats = new HashMap<>()
        {{
            put("Score", "1");
            put("Accuracy", "1");
            put("Score / Min", "1");
            put("Warfighter Nations score", "1");
            put("Win percentage", "1");
            put("Rank", "1");
            put("Time played", "1");
        }};
        when(playerScraper.getPlayerOverview(any())).thenReturn(mockedPlayerOverviewStats);
        PlayerOverview returnedPlayerOverview = playerService.getPlayerOverview("SOURI_HQ");
        assertAll("Should return player overview with no null fields",
                () -> assertEquals(mockedPlayerOverviewStats.get("Score"), returnedPlayerOverview.getScore()),
                () -> assertEquals(mockedPlayerOverviewStats.get("Accuracy"), returnedPlayerOverview.getAccuracy()),
                () -> assertEquals(mockedPlayerOverviewStats.get("Score / Min"), returnedPlayerOverview.getSpm()),
                () -> assertEquals(mockedPlayerOverviewStats.get("Warfighter Nations score"), returnedPlayerOverview.getNationsScore()),
                () -> assertEquals(mockedPlayerOverviewStats.get("Win percentage"), returnedPlayerOverview.getWinrate()),
                () -> assertEquals(mockedPlayerOverviewStats.get("Rank"), returnedPlayerOverview.getRank()),
                () -> assertEquals(mockedPlayerOverviewStats.get("Time played"), returnedPlayerOverview.getTimePlayed())
        );
    }

    @Test
    void shouldReturnNotNullPlayerDetailsFields() {
        Map<String, String> mockedPlayerDetailsStats = new HashMap<>()
        {{
            put("Max kills in a round", "1");
            put("Melee kills", "1");
            put("Max headshots in a round", "1");
            put("Max melee kills in a round", "1");
        }};
        when(playerScraper.getPlayerDetails(any())).thenReturn(mockedPlayerDetailsStats);
        PlayerDetails returnedPlayerDetails = playerService.getPlayerDetails("SOURI_HQ");
        assertAll("Should return player details with no null fields",
                () -> assertEquals(mockedPlayerDetailsStats.get("Max kills in a round"), returnedPlayerDetails.getMaxKillsInRound()),
                () -> assertEquals(mockedPlayerDetailsStats.get("Melee kills"), returnedPlayerDetails.getMeleeKills()),
                () -> assertEquals(mockedPlayerDetailsStats.get("Max headshots in a round"), returnedPlayerDetails.getMaxHeadshotsInRound()),
                () -> assertEquals(mockedPlayerDetailsStats.get("Max melee kills in a round"), returnedPlayerDetails.getMaxMeleeInRound())
        );
    }
}
