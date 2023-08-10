package com.thekingoftime.game.domains.game.shared;

public class Shared {

    private static Shared instance = new Shared();
    private Data data;

    private Shared() {
        data = new Data();
    }

    public static Shared getInstance() {
        return instance;
    }

    public Data getData() {
        return data;
    }
}
