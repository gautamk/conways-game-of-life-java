package com.gautamk.conway.gol.concepts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinatesTest {
    @Test
    void testConstructorCanary() {
        new Coordinates(3, 2);
    }

    @Test
    void testConstructorInvalidArgumentX() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-1, 3));
    }

    @Test
    void testConstructorInvalidArgumentY() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(4, -1));
    }

    @Test
    void testGetters() {
        Coordinates coordinates = new Coordinates(3, 2);
        assertEquals(3, coordinates.getX());
        assertEquals(2, coordinates.getY());
    }
}