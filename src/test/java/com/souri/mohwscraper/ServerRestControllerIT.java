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
class ServerRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnSuccess_getServers() throws Exception {
        mockMvc.perform(get("/servers"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnSuccess_getActiveServers() throws Exception {
        mockMvc.perform(get("/active-servers"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
