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
            for(int y=0;y<height;y++){
                Cell randomCell = this.board.get(x, y);
                assertNotNull(randomCell);
                assertFalse(randomCell.isAlive());
            }
        }
        assertEquals(width*height, board.getBoardMap().size());
    }
}