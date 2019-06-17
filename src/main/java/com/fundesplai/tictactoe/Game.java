package com.fundesplai.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game {

    private Space space;
    private List<Player> players;
    private int chainToWin;
    private int round;

    public Game() {

        space = new Space();
    }

    public void start() throws CloneNotSupportedException, PositionCantFollowVectorException {

        setRules();

        //Initialice turn control variables
        round = 0;
        boolean win = false;
        int turn = 0;
        int totalCells = Utils.getTotalCells(space);
        //While the game board isn't full and no one wins...
        while (round * players.size() + (turn + 1) < totalCells && !win) {

            if (turn == players.size()) {
                round++;
                turn = 0;
            }

            TUI.printTurnRound(round, players.get(turn));
            
            stateOfGame(Utils.generateInitialSpaceCursor(space));

            Position newPos = TUI.askPosition(space);
            while (isOccupied(newPos)) {

                TUI.printErrorExistingPosition();
                newPos = TUI.askPosition(space);
            }

            players.get(turn).addPosition(newPos);

            if (throughPlayerPositions(players.get(turn))) {

                win = true;
                TUI.winningMessage(players.get(turn));
            }

            turn++;
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
    private boolean isOccupied(Position pos) {

        for (Player player : players) {

            for (Position position : player.getPositions()) {

                if (pos.equals(position)) {

                    return true;
                }
            }
        }

        return false;
    }

    private boolean throughPlayerPositions(Player player)
            throws CloneNotSupportedException, PositionCantFollowVectorException {

        for (Position position : player.getPositions()) {

            Map<Vector, Position> adjacentPos = getAdjacentPositions(position);
            for (Map.Entry<Vector, Position> entry : adjacentPos.entrySet()) {

                Position vecPos = entry.getValue();
                int count = 1;
                while (player.hasPosition(vecPos)) {

                    count++;
                    vecPos = entry.getKey().nextPosFromGiven(vecPos);
                }

                if (count >= chainToWin) {

                    return true;
                }
            }
        }

        return false;
    }

    private Map<Vector, Position> getAdjacentPositions(Position position) throws CloneNotSupportedException, PositionCantFollowVectorException {

        Map<Vector, Position> adjacentPositions = new HashMap<Vector, Position>();
        
        Set<Combination> combinations = new HashSet<Combination>();
        Combinator combinator = new Combinator(Utils.fromAxisSetToList(position.getCoordinates().keySet()));

        Utils.getAllCombinations(combinations, combinator);

        List<Vector> vectors = new ArrayList<Vector>();
        for (Combination comb : combinations) {

            vectors.addAll(Utils.inicDirCombination(comb));
        }
        
        for (Vector vector : vectors) {

            Position adjacentPosition = vector.nextPosFromGiven(position);
            if (adjacentPosition != null) {

                adjacentPositions.put(vector, adjacentPosition);
            }
        }

        return adjacentPositions;
    }

    /**
     * Runs around the game board recursively and prints every position
     * @param sCursor
     * @param players
     * @throws CloneNotSupportedException
     */
    private void stateOfGame(SpaceCursor sCursor) throws CloneNotSupportedException {

        SpaceCursor nextSCursor = null;

        if (sCursor.stillDimensions()) {

            nextSCursor = sCursor.clone();
            nextSCursor.nextDimension();
            stateOfGame(nextSCursor);
        }

        if (!(sCursor.isOnLimit())) {

            nextSCursor = sCursor.clone();
            nextSCursor.nextCell();
            stateOfGame(nextSCursor);
        }

        TUI.printStateOfGame(sCursor, players);
    }

    public Space getSpace() {

        return space;
    }

    public List<Player> getPlayers() {
        return players;
    }
}