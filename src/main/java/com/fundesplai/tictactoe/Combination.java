package com.fundesplai.tictactoe;

import java.util.Arrays;

public class Combination {

    private Axis[] axisCombs;

    public Combination(Axis[] axisCombs) {

        this.axisCombs = axisCombs;
    }

    public Axis[] getAxisCombs() {

        return axisCombs;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(axisCombs);
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

        Combination other = (Combination) obj;
        if (this.axisCombs.length != other.getAxisCombs().length) {
            
            return false;
        }

        for (int i = 0 ; i < this.axisCombs.length ; i++) {

            boolean hasIt = false;
            for (int e = 0 ; e < other.getAxisCombs().length ; e++) {

                if (this.axisCombs[i].equals(getAxisCombs()[e])) {

                    hasIt = true;
                }
            }

            if (!hasIt) {

                return false;
            }
        }

        return true;
    }
}