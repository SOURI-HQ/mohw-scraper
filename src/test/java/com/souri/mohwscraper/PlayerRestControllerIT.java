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
                .andExpect(content().string("{\"score\":\"12,837,800\",\"accuracy\":\"29.9%\",\"spm\":\"471.59\",\"nationsScore\":\"6051\",\"winrate\":\"51.2%\",\"rank\":\"106\",\"timePlayed\":\"300h 27m\"}"));
    }

    @Test
    void shouldReturnSuccess_getPlayerDetails() throws Exception {
        mockMvc.perform(get("/player/SOURI_HQ/details"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"maxKillsInRound\":\"75\",\"meleeKills\":\"136\",\"maxHeadshotsInRound\":\"30\",\"maxMeleeInRound\":\"4\"}"));
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
}
