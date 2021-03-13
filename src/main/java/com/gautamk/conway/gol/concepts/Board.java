package com.gautamk.conway.gol.concepts;

import com.google.common.base.Preconditions;

import java.util.HashMap;

public class Board {
    private final int width;
    private final int height;
    private HashMap<Coordinates, Cell> boardMap;


    public Board(int width, int height) {
        Preconditions.checkArgument(width > 0);
        Preconditions.checkArgument(height > 0);
        this.width = width;
        this.height = height;
        this.boardMap = new HashMap<>(width * height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell get(Coordinates coordinates) {
        Preconditions.checkArgument(coordinates.getX() < width);
        Preconditions.checkArgument(coordinates.getY() < height);
        return boardMap.computeIfAbsent(coordinates, coordinates1 -> new Cell());
    }

    public Cell get(int x, int y) {
        return get(new Coordinates(x, y));
    }

    HashMap<Coordinates, Cell> getBoardMap() {
        return boardMap;
    }
}
