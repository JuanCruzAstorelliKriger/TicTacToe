package com.fundesplai.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Player {
    
    private String name;
    private char symbol;
    private List<Position> positions;

    public Player(String name, char symbol) {

        this.name = name;
        this.symbol = symbol;
        this.positions = new ArrayList<Position>();
    }

    public char getSymbol() {
        return symbol;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {

        this.positions = positions;
    }
    
    public boolean hasPosition(Position externalPos) {

        for (Position position : positions) {

            if (position.equals(externalPos)) {
                return true;
            }
        }

        return false;
    }

    public void addPosition(Position position) {

        positions.add(position);
    }

    public String getName() {

        return name;
    }
}