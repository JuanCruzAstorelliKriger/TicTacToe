package com.fundesplai.tictactoe;

import java.util.HashMap;
import java.util.Map;

public class Vector {

    private Map<Axis, Boolean> axisDirs;

    public Vector(Combination axis, boolean[] dirs) {

        axisDirs = new HashMap<Axis, Boolean>();

        Axis[] axisCombs = axis.getAxisCombs();
        if (axisCombs.length != dirs.length) {

            throw new VectorCompsException();
        }

        for (int i = 0 ; i < axisCombs.length ; i++) {

            axisDirs.put(axisCombs[i], dirs[i]);
        }
    }

    public Map<Axis, Boolean> getAxisDirs() {

        return axisDirs;
    }

    /**
     * Gets the immediate position corresponding to the current vector
     * 
     * @param pos The initial position
     * @return The next Position within the vector that executes the method
     * @throws CloneNotSupportedException
     * @throws PositionCantFollowVectorException
     */
    public Position nextPosFromGiven(Position pos)
            throws CloneNotSupportedException, PositionCantFollowVectorException {

        Position nextPos = pos.clone();
        //For every axis of the vector increments or decrements de value of the position for that axis, in function of the direction of the vector for that axis (positive or negative, true or false)
        for (Map.Entry<Axis, Boolean> entry : this.axisDirs.entrySet()) {

            if (nextPos.hasAxis(entry.getKey())) {

                //If the value can't be modified because it's on the limit return null
                if (!nextPos.inDeCrementValue(entry.getKey(), entry.getValue().booleanValue())) {

                    return null;
                }
            }
            else {

                throw new PositionCantFollowVectorException(nextPos, this);
            }
        }

        return nextPos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((axisDirs == null) ? 0 : axisDirs.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Vector other = (Vector) obj;
        
        //Checks if the combinations of axis of the vectors are the same:
            //- First by comparing the number of axis combinated on each
        if (this.axisDirs.size() != other.getAxisDirs().size()) {

            return false;
        }
        else {

                //- Then, if passes, by checking that in fact both combinations have the same axis (no matters the order)
            for (Axis axisThis : this.axisDirs.keySet()) {

                boolean exists = false;
                for (Axis axisOther : other.getAxisDirs().keySet()) {

                    if (axisThis.equals(axisOther)) {

                        exists = true;
                    }
                }

                if (!exists) {

                    return false;
                }
            }

            //At last, confirms that every axis has the same direction in both combinations (true or false, positive or negative dir)
            for (Axis axisThis : this.axisDirs.keySet()) {

                if (this.axisDirs.get(axisThis) != other.getAxisDirs().get(axisThis)) {

                    return false;
                }
            }

            return true;
        }
    }
}