package ru.afanasev.RestApp.util;

public class PlayerNotCreatedException extends RuntimeException {
    public PlayerNotCreatedException(String msg) {
        super(msg);
    }
}