package com.fundesplai.tictactoe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {

    private Space space;
    private List<Player> players;
    private int chainToWin;
    private int round;

    public Game() {

        space = new Space();
    }

    public void start() {

        setRules();

        //Initialice turn control variables
        round = 0;
        boolean win = false;
        int turn = 0;
        int totalCells = Utils.getTotalCells(space);
        //While the game board isn't full and no one wins...
        while (round * players.size() + (turn + 1) < totalCells || win) {

            if (turn == players.size()) {
                round++;
                turn = 0;
            }

            TUI.printTurnRound(round + 1, players.get(turn));
            

        }
    }

    private void setRules() {

        List<Axis> axis = TUI.askDimensions();
        space.setDimensions(axis);

        players = TUI.askPlayers();

        chainToWin = TUI.askChainToWin();
    }

    /**
     * Checks if the given position already exists on the game situation
     */
    public boolean isOccupied(Position pos) {

        for (Player player : players) {

            for (Position position : player.getPositions()) {

                if (pos.equals(position)) {

                    return true;
                }
            }
        }

        return false;
    }

    public void throughPlayerPositions(Player player) {

        for (Position position : player.getPositions()) {

            
        }
    }

    public List<Position> getAdjacentPositions(Position position) throws CloneNotSupportedException {

        List<Position> adjacentPositions = new ArrayList<Position>();
        
        Set<Combination> combinations = new HashSet<Combination>();
        Combinator combinator = new Combinator(Utils.fromSetToList(position.getCoordinates().keySet()));

        Utils.getAllCombinations(combinations, combinator);

        List<Vector> vectors = new ArrayList<Vector>();
        for (Combination comb : combinations) {

            vectors.addAll(Utils.inicDirCombination(comb));
        }
        
    }

    /**
     * Runs around the game board recursively and prints every position
     * @param sCursor
     * @param players
     * @throws CloneNotSupportedException
     */
    public static void stateOfGame(SpaceCursor sCursor, List<Player> players) throws CloneNotSupportedException {

        SpaceCursor nextSCursor = null;

        if (sCursor.stillDimensions()) {

            nextSCursor = sCursor.clone();
            nextSCursor.nextDimension();
            stateOfGame(nextSCursor, players);
        }

        if (!(sCursor.isOnLimit())) {

            nextSCursor = sCursor.clone();
            nextSCursor.nextCell();
            stateOfGame(nextSCursor, players);
        }

        TUI.printStateOfGame(sCursor, players);
    }

    public Space getSpace() {

        return space;
    }
}