package com.fundesplai.tictactoe;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Position implements Cloneable {

    /**
     * <Axis,positionInAxis>
     */
    private HashMap<Axis, Integer> coordinates;

    public Position() {

        this.coordinates = new LinkedHashMap<Axis, Integer>();
    }

    /**
     * Increments or decrements the value of the given axis in function of the given boolean (representing the direction, positive or negative)
     * @return true if the value was properly modified, false if the value couldn't been modified cause it was on the limit (initial or final, 0 or max)
     */
    public boolean inDeCrementValue(Axis axis, boolean direction) {

        int value = coordinates.get(axis);

        //If the value has to be incremented
        if (direction) {

            if (value == axis.getLimit()) {

                return false;
            }
        }
        //Else the value has to be decremented
        else {

            if (value == 0) {

                return false;
            }
        }

        coordinates.replace(axis, direction ? ++value : --value);
        return true;
    }

    public void addAxis(Axis axis, int value) {

        this.coordinates.put(axis, value);
    }

    public boolean hasAxis(Axis axis) {

        return coordinates.containsKey(axis);
    }

    public HashMap<Axis, Integer> getCoordinates() {

        return coordinates;
    }

    public void setCoordinates(HashMap<Axis, Integer> coordinates) {

        this.coordinates = coordinates;
    }

    @Override
    protected Position clone() throws CloneNotSupportedException {

        Position clone = (Position) super.clone();
        clone.setCoordinates(new LinkedHashMap<Axis, Integer>(coordinates));

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