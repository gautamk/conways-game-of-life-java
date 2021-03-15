package com.gautamk.conway.gol.concepts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Random random;
    private Board board;
    private int width;
    private int height;
    private int[] validXValues;
    private int[] validYValues;


    @BeforeEach
    void setUp() {
        this.random = new Random();
        this.width = 20;
        this.height = 20;
        this.validXValues = IntStream.range(0, width).toArray();
        this.validYValues = IntStream.range(0, height).toArray();
        this.board = new Board(width, height);
        // Make sure validXValues and validYValues are not empty
        assertNotEquals(0, validXValues.length);
        assertNotEquals(0, validYValues.length);
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
    void testInvalidGetLeftNeighbor() {
        for (int y : validYValues) {
            assertNull(
                    this.board.getLeftNeighbor(0, y)
            );
        }
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
    void testInvalidGetRightNeighbor() {
        for (int y : validYValues) {
            assertNull(
                    this.board.getRightNeighbor(width - 1, y)
            );
        }
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
    void testInvalidGetTopNeighbor() {
        for (int x : validXValues) {
            assertNull(
                    this.board.getTopNeighbor(x, 0)
            );
        }
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
    void testInvalidGetBottomNeighbor() {
        for (int x : validXValues) {
            assertNull(
                    this.board.getBottomNeighbor(x, height - 1)
            );
        }
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
    void testInvalidGetTopLeftNeighbor() {
        for (int x : validXValues) {
            assertNull(
                    this.board.getTopLeftNeighbor(x, 0)
            );
        }

        for (int y : validYValues) {
            assertNull(
                    this.board.getTopLeftNeighbor(0, y)
            );
        }
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
    void testInvalidGetTopRightNeighbor() {
        for (int x : validXValues) {
            assertNull(this.board.getTopRightNeighbor(x, 0));
        }

        for (int y : validYValues) {
            assertNull(this.board.getTopRightNeighbor(width - 1, y));
        }
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
    void testInvalidGetBottomLeftNeighbor() {
        // left most column
        for (int y : validYValues) {
            assertNull(
                    this.board.getBottomLeftNeighbor(0, y)
            );
        }
        // bottom row
        for (int x : validXValues) {
            assertNull(
                    this.board.getBottomLeftNeighbor(x, height - 1)
            );
        }
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
    void testInvalidGetBottomRightNeighbor() {
        // right most column
        for (int y : validYValues) {
            assertNull(
                    this.board.getBottomRightNeighbor(width - 1, y)
            );
        }
        // Bottom Row
        for (int x : validXValues) {
            assertNull(
                    this.board.getBottomRightNeighbor(x, height - 1)
            );
        }
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
    void testGetCellsWith8Neighbors() {
        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                Cell[] neighbors = this.board.getNeighbors(x, y);
                assertEquals(8, neighbors.length);
            }
        }
    }

    @Test
    void testGetNeighborsTopRow() {
        for (int x : validXValues) {
            Cell[] neighbors = this.board.getNeighbors(x, 0);
            if (x == 0 || x == width - 1) {
                assertEquals(3, neighbors.length);
            } else {
                assertEquals(5, neighbors.length);
            }
        }

    }

    @Test
    void testGetNeighborsBottomRow() {
        for (int x : validXValues) {
            Cell[] neighbors = this.board.getNeighbors(x, height - 1);
            if (x == 0 || x == width - 1) {
                assertEquals(3, neighbors.length);
            } else {
                assertEquals(5, neighbors.length);
            }
        }
    }

    @Test
    void testGetNeighborsLeftMostColumn() {
        for (int y : validYValues) {
            Cell[] neighbors = this.board.getNeighbors(0, y);
            if (y == 0 || y == height - 1) {
                assertEquals(3, neighbors.length);
            } else {
                assertEquals(5, neighbors.length);
            }
        }
    }

    @Test
    void testGetNeighborsRightMostColumn() {
        for (int y : validYValues) {
            Cell[] neighbors = this.board.getNeighbors(width - 1, y);
            if (y == 0 || y == height - 1) {
                assertEquals(3, neighbors.length);
            } else {
                assertEquals(5, neighbors.length);
            }
        }
    }

    @Test
    void testPut() {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        Cell cell = this.board.get(x, y);
        Cell expected = new Cell(!cell.isAlive());
        this.board.put(x, y, expected);
        assertSame(expected, this.board.get(x, y));
    }

    @Test
    void testNullCoordinatesPut() {
        assertThrows(NullPointerException.class, () ->
                this.board.put(null,
                        new Cell(random.nextBoolean())
                )
        );
    }

    @Test
    void testNullCellPut() {
        assertThrows(NullPointerException.class, () ->
                this.board.put(
                        random.nextInt(width),
                        random.nextInt(height),
                        null)
        );
    }

    @Test
    void testCellPutLiterals() {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        boolean isAlive = random.nextBoolean();
        this.board.put(x, y, isAlive);
        Cell actual = this.board.get(x, y);
        assertEquals(isAlive, actual.isAlive());
    }
}