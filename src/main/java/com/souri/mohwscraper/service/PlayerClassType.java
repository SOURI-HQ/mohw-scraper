package com.souri.mohwscraper.service;

public enum PlayerClassType {
    c_1024("Spec Ops"),
    c_512("Pointman"),
    c_256("Assaulter"),
    c_128("Demolition"),
    c_32("Heavy Gunner"),
    c_8("Sniper");

    public final String returnedTypeName;

    PlayerClassType(String returnedTypeName) {
        this.returnedTypeName = returnedTypeName;
    }
}
