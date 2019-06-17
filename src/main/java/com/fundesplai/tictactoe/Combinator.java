package com.fundesplai.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Combinator implements Cloneable {

    //Elements to combine
    private List<Axis> axis;
    //A collection of pointers to referenciate simultaneously different elements of axisIndexes
    private List<Integer> pointers;
    //An auxiliar pointer that refers to a pointer of the list pointers in order to increment them and add new pointers
    private int auxPointer;

    public Combinator(List<Axis> axis) {

        this.axis = axis;
        this.pointers = new ArrayList<Integer>();
        this.pointers.add(0);
        this.auxPointer = 0;
    }

    public Integer getFirstPointer() {

        return pointers.get(0);
    }

    public boolean isFirstPointerAtLastPosition() {

        return pointers.get(0) == axis.size() - pointers.size();
    }

    /**
     * Determines if the pointer indicated by auxPointer can be incremented or not
     * 
     * @return
     */
    public boolean isntCurrentPointerAtLastPosition() {

        return pointers.get(auxPointer) < axis.size() - (pointers.size() - auxPointer);
    }

    /**
     * Used to determine if the auxiliar pointer is pointing to the last pointer of the list
     * @return
     */
    public boolean pointersToGo() {

        return auxPointer < pointers.size() - 1;
    }

    /**
     * Increments in 1 the position of every pointer from the index by auxPointer
     */
    public void shiftPointers() {

        for (int i = auxPointer; i < pointers.size(); i++) {

            pointers.set(i, pointers.get(i) + 1);
        }
    }

    /**
     * Adds a pointer to the list and sets them all to their initial position (one
     * after the other, one at a time, from 0)
     */
    public void addPointerAndReset() {

        int i;
        for (i = 0; i < pointers.size(); i++) {

            pointers.set(i, i);
        }

        pointers.add(i);
    }

    public void incrementAuxiliarPointer() {

        this.auxPointer++;
    }

    /**
     * Gets the axisIndexes indicated by the current pointers and creates and return an object Combinator 
     */
    public Combination getCombination() {

        Axis[] combination = new Axis[pointers.size()];
        int i = 0;
        for (Integer pointer : pointers) {

            combination[i] = axis.get(pointer);
            i++;
        }

        return new Combination(combination);
    }

    private void setPointers(List<Integer> pointers) {

        this.pointers = pointers;
    }

    @Override
    protected Combinator clone() throws CloneNotSupportedException {

        Combinator clone = (Combinator) super.clone();

        List<Integer> clonedPointers = new ArrayList<Integer>();
        for (Integer pointer : pointers) {

            clonedPointers.add(pointer);
        }
        clone.setPointers(clonedPointers);

        return clone;
    }
}