package com.fundesplai.tictactoe;

public class AxisNameGenerator {

    private static final String[] units = {"x", "y", "z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w"};

    public static String get(int index) {

        return units[index / units.length] + units[index % units.length];
    }
}