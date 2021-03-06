package com.gautamk.conway.gol.concepts;

import com.google.common.base.Preconditions;

public class Coordinates {

    public final int x;
    public final int y;

    public Coordinates(int x, int y) {
        Preconditions.checkArgument(x >= 0);
        Preconditions.checkArgument(y >= 0);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
