package com.fundesplai.tictactoe;

import java.util.HashMap;
import java.util.Map;

public class Vector {

    private Map<Integer, Boolean> axisDirs;

    public Vector(Combination axis, boolean[] dirs) {

        axisDirs = new HashMap<Integer, Boolean>();

        Integer[] axisCombs = axis.getAxisCombs();
        if (axisCombs.length !=  dirs.length) {

            throw new VectorCompsException();
        }

        for (int i = 0 ; i < axisCombs.length ; i++) {

            axisDirs.put(axisCombs[i], dirs[i]);
        }
    }

    public Map<Integer, Boolean> getAxisDirs() {

        return axisDirs;
    }

    /**
     * Gets the immediate position corresponding to the current vector
     * @param pos
     * @return
     */
    public Position nextPosFromGiven(Position pos) {

        
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
            for (Integer axisThis : this.axisDirs.keySet()) {

                boolean exists = false;
                for (Integer axisOther : other.getAxisDirs().keySet()) {

                    if (axisThis.intValue() == axisOther.intValue()) {

                        exists = true;
                    }
                }

                if (!exists) {

                    return false;
                }
            }

            //At last, confirms that every axis has the same direction in both combinations (true or false, positive or negative dir)
            for (Integer axisThis : this.axisDirs.keySet()) {

                if (this.axisDirs.get(axisThis) != other.getAxisDirs().get(axisThis)) {

                    return false;
                }
            }

            return true;
        }
    }
}