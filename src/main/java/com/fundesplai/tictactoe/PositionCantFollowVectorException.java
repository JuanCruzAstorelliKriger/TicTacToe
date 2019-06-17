package com.fundesplai.tictactoe;

public class PositionCantFollowVectorException extends Exception {

    private static final long serialVersionUID = -1506347648389206029L;

    public PositionCantFollowVectorException(Position pos, Vector vec) {

        super("The " + pos.toString() + " isn't set in all the axis in which the " + vec.toString() + " compels it to move.");
    }
}