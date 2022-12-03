package com.souri.mohwscraper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PlayerRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnSuccess_getPlayerOverview() throws Exception {
        mockMvc.perform(get("/player/SOURI_HQ/overview"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"score\":\"12915500\",\"accuracy\":\"29.865059417778973\",\"spm\":\"471.3534952900347\",\"nationsScore\":\"6051\",\"winrate\":\"50.9375\",\"rank\":\"106\",\"timePlayed\":\"302.55\"}"));
    }

    @Test
    void shouldReturnSuccess_getPlayerDetails() throws Exception {
        mockMvc.perform(get("/player/SOURI_HQ/details"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"maxKillsInRound\":\"75\",\"meleeKills\":\"136\",\"maxHeadshotsInRound\":\"30\",\"maxMeleeInRound\":\"4\",\"kills\":\"42386\",\"deaths\":\"19074\"}"));
    }

    @Test
    void shouldReturnSuccess_getPlayerClasses() throws Exception {
        mockMvc.perform(get("/player/SOURI_HQ/classes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"classType\":\"Assaulter\",\"kills\":\"15675\",\"deaths\":\"7037\",\"score\":\"3218480\",\"time\":\"113.93777777777778\",\"killStreak\":\"22\",\"headshots\":\"2778\",\"longestHeadshot\":\"346\",\"headshotsInRound\":\"14\",\"maxScoreInRound\":\"12137\",\"maxKillsInRound\":\"65\"},{\"classType\":\"Spec Ops\",\"kills\":\"5397\",\"deaths\":\"2466\",\"score\":\"1150500\",\"time\":\"42.37277777777778\",\"killStreak\":\"26\",\"headshots\":\"1018\",\"longestHeadshot\":\"135\",\"headshotsInRound\":\"15\",\"maxScoreInRound\":\"9901\",\"maxKillsInRound\":\"54\"},{\"classType\":\"Pointman\",\"kills\":\"15150\",\"deaths\":\"6181\",\"score\":\"2891600\",\"time\":\"87.68944444444445\",\"killStreak\":\"32\",\"headshots\":\"2970\",\"longestHeadshot\":\"297\",\"headshotsInRound\":\"16\",\"maxScoreInRound\":\"13792\",\"maxKillsInRound\":\"75\"},{\"classType\":\"Demolition\",\"kills\":\"582\",\"deaths\":\"294\",\"score\":\"114378\",\"time\":\"5.004166666666666\",\"killStreak\":\"10\",\"headshots\":\"72\",\"longestHeadshot\":\"69\",\"headshotsInRound\":\"5\",\"maxScoreInRound\":\"4728\",\"maxKillsInRound\":\"26\"},{\"classType\":\"Sniper\",\"kills\":\"4715\",\"deaths\":\"2562\",\"score\":\"998213\",\"time\":\"45.77055555555555\",\"killStreak\":\"28\",\"headshots\":\"2882\",\"longestHeadshot\":\"803\",\"headshotsInRound\":\"30\",\"maxScoreInRound\":\"9191\",\"maxKillsInRound\":\"50\"},{\"classType\":\"Heavy Gunner\",\"kills\":\"867\",\"deaths\":\"534\",\"score\":\"183054\",\"time\":\"7.092222222222222\",\"killStreak\":\"14\",\"headshots\":\"149\",\"longestHeadshot\":\"113\",\"headshotsInRound\":\"8\",\"maxScoreInRound\":\"9306\",\"maxKillsInRound\":\"43\"}]"));
    }

    @Test
    void shouldReturnFail_getPlayerOverview() throws Exception {
        mockMvc.perform(get("/player/doesntexistqwerty/overview"))
                .andDo(print())
                .andExpect(status().is(400));
    }

    @Test
    void shouldReturnFail_getPlayerDetails() throws Exception {
        mockMvc.perform(get("/player/doesntexistqwerty/details"))
                .andDo(print())
                .andExpect(status().is(400));
    }

    @Test
    void shouldReturnFail_GetPlayerClasses() throws Exception {
        mockMvc.perform(get("/player/doesntexistqwerty/classes"))
                .andDo(print())
                .andExpect(status().is(400));
    }
}
