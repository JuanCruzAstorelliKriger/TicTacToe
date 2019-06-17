package com.fundesplai.tictactoe;

public class SpaceCursor implements Cloneable {

    private Space space;
    private Position position;
    private int currentAxis;

    public SpaceCursor(Space space, Position position, int currentAxis) {

        this.space = space;
        this.position = position;
        this.currentAxis = currentAxis;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {

        this.position = position;
    }

    public int getCurrentAxis() {

        return currentAxis;
    }

    public Space getSpace() {

        return space;
    }

    /**
     * Check if remain axis to transit through
     */
    public boolean stillDimensions() {

        return currentAxis < position.getCoordinates().size() - 1;
    }

    /**
     * Check if the cursor is actually on the limit of the current axis, and
     * therefore can't move on to the next cell of it
     */
    public boolean isOnLimit() {

        return position.getCoordinates().get(space.getAxis(currentAxis)) == space.getAxis(currentAxis).getLimit() - 1;

    }

    /**
     * Check if the cursor is acually on the first cell of the axis. (For printing purposes only)
     */
    public boolean isOnInit() {

        return position.getCoordinates().get(space.getAxis(currentAxis)) == 0;
    }

    /**
     * Moves to the next axis of the space
     */
    public void nextDimension() {

        currentAxis++;
    }

    /**
     * Increments the position of the cursor within the current axis
     */
    public void nextCell() {

        int currentPos = position.getCoordinates().get(space.getAxis(currentAxis));
        position.getCoordinates().replace(space.getAxis(currentAxis), currentPos + 1);
    }

    @Override
    protected SpaceCursor clone() throws CloneNotSupportedException {

        SpaceCursor clone = (SpaceCursor) super.clone();
        clone.setPosition(position.clone());

        return clone;
    }

    @Override
    public String toString() {
        return "SpaceCursor [currentAxis=" + currentAxis + ", position=" + position + ", space=" + space + "]";
    }
}