package com.gautamk.conway.gol.concepts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Random random;
    private Board board;
    private int width;
    private int height;

    @BeforeEach
    void setUp() {
        this.random = new Random();
        this.width = 20;
        this.height = 20;
        this.board = new Board(width, height);
    }

    @Test
    void testConstructorCorrectCanary() {
        Board board = new Board(5, 5);
        assertNotNull(board);
    }

    @Test
    void testConstructorZeroWidth() {
        assertThrows(IllegalArgumentException.class, () -> new Board(0, 5));
    }

    @Test
    void testConstructorZeroHeight() {
        assertThrows(IllegalArgumentException.class, () -> new Board(5, 0));
    }

    @Test
    void testGetWidth() {
        int width = random.nextInt(100);
        int height = random.nextInt(100);
        width = width > 0 ? width : 1;
        height = height > 0 ? height : 1;

        Board board = new Board(width, height);
        assertEquals(width, board.getWidth());
    }

    @Test
    void testGetHeight() {
        int width = random.nextInt(100);
        int height = random.nextInt(100);
        width = width > 0 ? width : 1;
        height = height > 0 ? height : 1;

        Board board = new Board(width, height);
        assertEquals(height, board.getHeight());
    }

    @Test
    void testValidGetLazyInitialization() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell randomCell = this.board.get(x, y);
                assertNotNull(randomCell);
                assertFalse(randomCell.isAlive());
            }
        }
        assertEquals(width * height, board.getBoardMap().size());
    }

    @Test
    void testInvalidGet() {
        assertThrows(IllegalArgumentException.class, () -> this.board.get(width, height - 2));
        assertThrows(IllegalArgumentException.class, () -> this.board.get(width - 2, height));
        assertThrows(IllegalArgumentException.class, () -> this.board.get(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> this.board.get(0, -1));
    }


    @Test
    void testValidGetLeftNeighbor() {
        int x = 5;
        int y = 5;
        Cell expected = this.board.get(x - 1, y);
        Cell actual = this.board.getLeftNeighbor(x, y);
        assertSame(expected, actual);
    }

    @Test
    void testValidGetRightNeighbor() {
        int x = 5;
        int y = 5;
        Cell expected = this.board.get(x + 1, y);
        Cell actual = this.board.getRightNeighbor(x, y);
        assertSame(expected, actual);
    }

    @Test
    void testValidGetTopNeighbor() {
        int x = 5;
        int y = 5;
        Cell expected = this.board.get(x, y - 1);
        Cell actual = this.board.getTopNeighbor(x, y);
        assertSame(expected, actual);
    }

    @Test
    void testValidGetBottomNeighbor() {
        int x = 5;
        int y = 5;
        Cell expected = this.board.get(x, y + 1);
        Cell actual = this.board.getBottomNeighbor(x, y);
        assertSame(expected, actual);
    }

    @Test
    void testValidGetTopLeftNeighbor() {
        int x = 5;
        int y = 5;
        Cell expected = this.board.get(x - 1, y - 1);
        Cell actual = this.board.getTopLeftNeighbor(x, y);
        assertSame(expected, actual);
    }

    @Test
    void testValidGetTopRightNeighbor() {
        int x = 5;
        int y = 5;
        Cell expected = this.board.get(x + 1, y - 1);
        Cell actual = this.board.getTopRightNeighbor(x, y);
        assertSame(expected, actual);
    }

    @Test
    void testValidGetBottomLeftNeighbor() {
        int x = 5;
        int y = 5;
        Cell expected = this.board.get(x - 1, y + 1);
        Cell actual = this.board.getBottomLeftNeighbor(x, y);
        assertSame(expected, actual);
    }

    @Test
    void testValidGetBottomRightNeighbor() {
        int x = 5;
        int y = 5;
        Cell expected = this.board.get(x + 1, y + 1);
        Cell actual = this.board.getBottomRightNeighbor(x, y);
        assertSame(expected, actual);
    }

    @Test
    void testGetNeighbors() {
    }
}