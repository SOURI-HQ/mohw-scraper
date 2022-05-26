package com.souri.mohwscraper.services;

import com.souri.mohwscraper.domain.Server;

import java.util.List;

public interface ServerService {
    List<Server> getServers();
    List<Server> getActiveServers();
}
