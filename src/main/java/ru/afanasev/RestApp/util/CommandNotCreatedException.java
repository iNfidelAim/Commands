package ru.afanasev.RestApp.util;

public class CommandNotCreatedException extends RuntimeException {
    public CommandNotCreatedException(String msg) {
        super(msg);
    }
}
