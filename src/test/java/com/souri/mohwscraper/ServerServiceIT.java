package com.souri.mohwscraper;

import com.souri.mohwscraper.domain.Server;
import com.souri.mohwscraper.services.ServerServiceImpl;
import com.souri.mohwscraper.util.ServerScraper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ServerServiceIT {

    @Autowired
    private ServerServiceImpl serverService;

    @MockBean
    private ServerScraper serverScraper;

    @Test
    void shouldReturnNotNullServerFields() {
        Map<String, String> server = new HashMap<>()
        {{
            put("name", "1");
            put("players", "1");
            put("map", "1");
            put("mode", "1");
        }};
        List<Map<String, String>> mockedServers = new LinkedList<>();
        mockedServers.add(server);
        when(serverScraper.fetchAllServers()).thenReturn(mockedServers);
        List<Server> returnedServers = serverService.getServers();
        assertAll("Should return servers details with no null fields",
                () -> assertEquals(mockedServers.get(0).get("name"), returnedServers.get(0).getName()),
                () -> assertEquals(mockedServers.get(0).get("players"), returnedServers.get(0).getPlayerCount()),
                () -> assertEquals(mockedServers.get(0).get("map"), returnedServers.get(0).getMap()),
                () -> assertEquals(mockedServers.get(0).get("mode"), returnedServers.get(0).getGamemode())
        );
    }

    @Test
    void shouldReturnNotNullActiveServerFields() {
        Map<String, String> server = new HashMap<>()
        {{
            put("name", "1");
            put("players", "1");
            put("map", "1");
            put("mode", "1");
        }};
        List<Map<String, String>> mockedActiveServers = new LinkedList<>();
        mockedActiveServers.add(server);
        when(serverScraper.fetchAllServers()).thenReturn(mockedActiveServers);
        List<Server> returnedServers = serverService.getActiveServers();
        assertAll("Should return active servers details with no null fields",
                () -> assertEquals(mockedActiveServers.get(0).get("name"), returnedServers.get(0).getName()),
                () -> assertEquals(mockedActiveServers.get(0).get("players"), returnedServers.get(0).getPlayerCount()),
                () -> assertEquals(mockedActiveServers.get(0).get("map"), returnedServers.get(0).getMap()),
                () -> assertEquals(mockedActiveServers.get(0).get("mode"), returnedServers.get(0).getGamemode())
        );
    }
}
