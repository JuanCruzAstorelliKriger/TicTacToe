package com.fundesplai.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Space {

    private List<Axis> axis;

    /* public Space() {
        axis = new ArrayList<Axis>();
    } */

    public void setDimensions(List<Axis> axis) {

        this.axis = axis;
    }

    public List<Axis> getDimensions() {
        return axis;
    }

    public Axis getAxis(int index) {
        
        return axis.get(index);
    }
}