package com.fundesplai.tictactoe;

public class Axis {

    private int index;
    private int limit;

    public Axis (int limit, int index) {

        this.limit = limit;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + index;
        result = prime * result + limit;
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
            
        Axis other = (Axis) obj;
        if (index != other.index)
            return false;
        if (limit != other.limit)
            return false;
        return true;
    }
}