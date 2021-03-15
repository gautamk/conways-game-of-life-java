package com.gautamk.conway.gol.rules;

import com.gautamk.conway.gol.concepts.Board;
import com.gautamk.conway.gol.concepts.Cell;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Applies the rules of the Game of life on the whole board.
 */
public class BoardRules implements Consumer<Board> {

    @Override
    public void accept(Board board) {
        int height = board.getHeight();
        int width = board.getWidth();
        boolean[][] changeset = new boolean[height][width];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell cell = board.get(x, y);
                List<Cell> aliveNeighbors = board.getNeighbors(x, y).stream().filter(Cell::isAlive).collect(Collectors.toList());
                if (cell.isAlive()) {
                    switch (aliveNeighbors.size()) {
                        case 2, 3 -> changeset[y][x] = true;
                        default -> changeset[y][x] = false;
                    }
                } else {
                    changeset[y][x] = aliveNeighbors.size() == 3;
                }
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board.put(x, y, changeset[y][x]);
            }
        }
    }
}
