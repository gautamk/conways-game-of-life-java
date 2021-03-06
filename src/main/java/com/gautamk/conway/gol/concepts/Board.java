package com.gautamk.conway.gol.concepts;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
    private final int width;
    private final int height;
    private final HashMap<Coordinates, Cell> boardMap;


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

    Cell get(Coordinates coordinates) {
        Preconditions.checkArgument(coordinates.getX() < width);
        Preconditions.checkArgument(coordinates.getY() < height);
        return boardMap.computeIfAbsent(coordinates, coordinates1 -> new Cell());
    }

    public Cell get(int x, int y) {
        return get(new Coordinates(x, y));
    }

    Cell getLeftNeighbor(int x, int y) {
        try {
            return get(x - 1, y);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    Cell getRightNeighbor(int x, int y) {
        try {
            return get(x + 1, y);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    Cell getTopNeighbor(int x, int y) {
        try {
            return get(x, y - 1);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    Cell getBottomNeighbor(int x, int y) {
        try {
            return get(x, y + 1);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    Cell getTopLeftNeighbor(int x, int y) {
        try {
            return get(x - 1, y - 1);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    Cell getTopRightNeighbor(int x, int y) {
        try {
            return get(x + 1, y - 1);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    Cell getBottomLeftNeighbor(int x, int y) {
        try {
            return get(x - 1, y + 1);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    Cell getBottomRightNeighbor(int x, int y) {
        try {
            return get(x + 1, y + 1);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public List<Cell> getNeighbors(int x, int y) {
        Stream<Cell> neighbors = Stream.of(
                getLeftNeighbor(x, y),
                getRightNeighbor(x, y),
                getTopNeighbor(x, y),
                getBottomNeighbor(x, y),
                getBottomLeftNeighbor(x, y),
                getTopLeftNeighbor(x, y),
                getTopRightNeighbor(x, y),
                getBottomRightNeighbor(x, y)
        );
        return neighbors.filter(Objects::nonNull).collect(Collectors.toList());
    }

    Cell put(Coordinates coordinates, Cell cell) {
        Preconditions.checkNotNull(coordinates);
        Preconditions.checkNotNull(cell);
        this.boardMap.put(coordinates, cell);
        return cell;
    }

    public Cell put(int x, int y, Cell cell) {
        return put(new Coordinates(x, y), cell);
    }

    public Cell put(int x, int y, boolean isAlive) {
        return put(x, y, new Cell(isAlive));
    }

    HashMap<Coordinates, Cell> getBoardMap() {
        return boardMap;
    }
}
