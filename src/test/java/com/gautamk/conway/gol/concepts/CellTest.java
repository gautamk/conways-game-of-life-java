package com.gautamk.conway.gol.concepts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CellTest {
    private Random random;

    @BeforeEach
    void setUp() {
        this.random = new Random();
    }

    @Test
    void testConstructCanary() {
        new Cell(random.nextBoolean());
    }

    @Test
    void testGetExpectedValueFromConstruction() {
        boolean randomIsAlive = random.nextBoolean();
        Cell cell = new Cell(randomIsAlive);
        assertEquals(randomIsAlive, cell.isAlive());
    }

    @Test
    void testGetExpectedValueFromSetter() {
        boolean randomIsAlive = random.nextBoolean();
        Cell cell = new Cell(randomIsAlive);
        cell.setAlive(!randomIsAlive);
        assertEquals(!randomIsAlive, cell.isAlive());
    }

    @Test
    void testDefaultConstructorDeadCell() {
        assertFalse(new Cell().isAlive());
    }
}