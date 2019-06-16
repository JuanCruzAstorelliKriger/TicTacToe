package com.fundesplai.tictactoe;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Position implements Cloneable {

    /**
     * <IndexOfAxis,positionInAxis>
     */
    private HashMap<Integer, Integer> coordinates;

    public Position() {

        this.coordinates = new LinkedHashMap<Integer, Integer>();
    }

    /**
     * 
     * @param axisIndex
     * @return
     */
    /* public boolean incrementValue(int axisIndex) {

    }

    public boolean decrementValue(int axisIndex) {
        
    } */

    public void addAxis(int axisIndex, int value) {

        this.coordinates.put(axisIndex, value);
    }

    public HashMap<Integer, Integer> getCoordinates() {

        return coordinates;
    }

    public void setCoordinates(HashMap<Integer, Integer> coordinates) {

        this.coordinates = coordinates;
    }

    @Override
    protected Position clone() throws CloneNotSupportedException {

        Position clone = (Position) super.clone();
        clone.setCoordinates(new LinkedHashMap<Integer, Integer>(coordinates));

        return clone;
    }

    /**
     * This comparison method assumes that the values for every axis were introduced in both positions in the same order (the program is supposed to respect the order on position creations)
     */
    @Override
    public boolean equals(Object obj) throws IncongruentDimensionsPointException {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Position other = (Position) obj;
        
        return coordinates.equals(other.getCoordinates());
        /* Integer[] thisDimPoints = (Integer[]) coordinates.values().toArray();
        Integer[] otherDimPoints = (Integer[]) other.getCoordinates().values().toArray();

        if (thisDimPoints.length != otherDimPoints.length) {

            throw new IncongruentDimensionsPointException(this, other);
        }

        for (int i = 0 ; i < thisDimPoints.length ; i++) {

            if (thisDimPoints[i] != otherDimPoints[i]) {

                return false;
            }
        }

        return true; */
    }

    @Override
    public String toString() {
        return "Position [coordinates=" + coordinates + "]";
    }
}