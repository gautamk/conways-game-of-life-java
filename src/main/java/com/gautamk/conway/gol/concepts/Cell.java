package com.gautamk.conway.gol.concepts;

/**
 * A cell which can either be alive or dead based on GOL state
 */
public class Cell {
    private boolean isAlive;

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public Cell() {
        this.isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    /**
     * Toggles the cell's state
     * Kills the cell, if its alive
     * Resurrects the cell, if its dead
     */
    public void toggle() {
        this.isAlive = !this.isAlive;
    }
}
