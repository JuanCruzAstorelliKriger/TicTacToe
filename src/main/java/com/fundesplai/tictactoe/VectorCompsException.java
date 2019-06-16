package com.fundesplai.tictactoe;

public class VectorCompsException extends RuntimeException {

    private static final long serialVersionUID = -2288628656867141525L;

    public VectorCompsException() {

            super("Error at creating vector. The number of axis and the supposed directions for each aren't equal.");
        }
}