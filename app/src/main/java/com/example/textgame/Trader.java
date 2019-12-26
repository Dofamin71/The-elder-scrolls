package com.example.textgame;

public class Trader {
    public Trader() {

    }

    public String trader;

    void trade(int gold) {
        trader = ("-- \"Лучшие товары по лучшим ценам!\" --\n\n" +
                "       У тебя есть " + gold + " золота.\n\n" +
                "1) Зелье здоровья - 50 золотых.\n" +
                "2) Зелье опыта - 100 золотых.\n\n\n");
    }
}
