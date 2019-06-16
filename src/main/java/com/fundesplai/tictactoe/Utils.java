package com.fundesplai.tictactoe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

    /**
     * 
     * @param space -> The space wanted to be transited.
     * @return -> An spaceCursor initializated to zero
     */
    public static SpaceCursor generateInitialSpaceCursor(Space space) {

        return new SpaceCursor(space, getZeroPosition(space), 0);
    }

    /**
     * Generates a position at 0 to every axis of the given space
     * @param space
     * @return
     */
    public static Position getZeroPosition(Space space) {

        Position pos = new Position();

        for (Axis axis : space.getDimensions()) {
            
            pos.addAxis(axis.getIndex(), 0);
        }
    
        return pos;
    }

    /**
     * Checks if the given position belongs to any player and returns its own distinctive char or the default char representing an empty slot
     */
    public static char determinePositionSymbol(Position position, List<Player>  players) {

        for (Player player : players) {

            for (Position playerPos : player.getPositions()) {

                if (position.equals(playerPos)) {

                    return player.getSymbol();
                }
            }
        }

        return '-';
    }

    /**
     * Makes sure that the given name isn't being already used by any Player of the game
    */
    public static boolean nameIsTaken(List<Player> players, String name) {

        for (Player player : players) {

            if (name.equals(player.getName())) {
                
                return true;
            }
        }

        return false;
    }

    /**
     * Makes sure that the given char (the symbol representing the player at the game board) isn't being already used by any Player of the game
    */
    public static boolean charIsAlreadyUsed(List<Player> players, char symbol) {

        for (Player player : players) {

            if (player.getSymbol() == symbol) {

                return true;
            }
        }

        return false;
    }

    /**
     * From a collection of axis gets all the possible combinations of them in order to determine all the possible lines of movement
     * @param combinations
     * @param combinator
     * @throws CloneNotSupportedException
     */
    public static void getAllCombinations(Set<Combination> combinations, Combinator combinator)
            throws CloneNotSupportedException {

        Combinator nextCombinator = null;

        if (combinator.pointersToGo()) {

            nextCombinator = combinator.clone();
            nextCombinator.incrementAuxiliarPointer();
            getAllCombinations(combinations, nextCombinator);
        }

        if (combinator.isntCurrentPointerAtLastPosition()) {

            nextCombinator = combinator.clone();
            nextCombinator.shiftPointers();
            getAllCombinations(combinations, nextCombinator);
        }

        if (combinator.isFirstPointerAtLastPosition()) {

            if (combinator.getFirstPointer() != 0) {

                nextCombinator = combinator.clone();
                nextCombinator.addPointerAndReset();
                getAllCombinations(combinations, nextCombinator);
            }
        }

        combinations.add(combinator.getCombination());
    }

    public static void getAllDirectionsFromCombination(Set<Vector> directions, Combination comb, boolean[] dirCombs, int auxPointer) {

        if (!dirCombs[auxPointer]) {

            boolean[] clonedDirCombs = dirCombs.clone();
            clonedDirCombs[auxPointer] = true;
            getAllDirectionsFromCombination(directions, comb, clonedDirCombs, auxPointer);
        }

        if (auxPointer < dirCombs.length - 1) {

            getAllDirectionsFromCombination(directions, comb, dirCombs.clone(), auxPointer + 1);
        }

        directions.add(new Vector(comb, dirCombs));
    }

    public static Set<Vector> inicDirCombination(Combination comb) {

        Set<Vector> vectors = new HashSet<Vector>();
        boolean[] dirs = new boolean[comb.getAxisCombs().length];
        for (int i = 0 ; i < dirs.length ; i++) {

            dirs[i] = false;
        }

        getAllDirectionsFromCombination(vectors, comb, dirs, 0);

        return vectors;
    }

    public static int getTotalCells(Space space) {

        int total = 1;
        for (Axis axis : space.getDimensions()) {

            total *= axis.getLimit();
        }

        return total;
    }

    public static List<Integer> fromSetToList(Set<Integer> set) {

        List<Integer> list = new ArrayList<Integer>();
        for (Integer elem : set) {

            list.add(elem);
        }

        return list;
    }
}