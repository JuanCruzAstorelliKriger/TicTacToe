package com.fundesplai.tictactoe;

public class IncongruentDimensionsPointException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IncongruentDimensionsPointException(Position first, Position second) {

        super("The points to be compared (" + first.toString() + " and " + second.toString() + ") do not have matching dimensions/axis.");
    }
}