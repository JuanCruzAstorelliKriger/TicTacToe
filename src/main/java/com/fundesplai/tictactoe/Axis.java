package com.fundesplai.tictactoe;

public class Axis {

    private int index;
    private int limit;

    public Axis (int limit, int index) {

        this.limit = limit;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public int getLimit() {
        return limit;
    }
}