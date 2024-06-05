package com.kata.trimmer.service;

import com.kata.trimmer.model.Coordinates;
import com.kata.trimmer.model.Trimmer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionHandlerTest {

    private PositionHandler positionHandler;

    @BeforeEach
    public void setUp() {
        positionHandler = new PositionHandler();
    }

    @Test
    public void testMoveNorth() {
        Trimmer trimmer = setUpTrimmer(0, 0, "N", "A");
        Coordinates finalPosition = positionHandler.DetermineFinalPosition(trimmer);
        assertEquals(0, finalPosition.getX());
        assertEquals(1, finalPosition.getY());
        assertEquals("N", finalPosition.getDirection());
    }

    @Test
    public void testTurnLeftAndMoveWest() {
        Trimmer trimmer = setUpTrimmer(1, 1, "N", "GA");
        Coordinates finalPosition = positionHandler.DetermineFinalPosition(trimmer);
        assertEquals(0, finalPosition.getX());
        assertEquals(1, finalPosition.getY());
        assertEquals("W", finalPosition.getDirection());
    }

    @Test
    public void testTurnRightAndMoveEast() {
        Trimmer trimmer = setUpTrimmer(1, 1, "N", "DA");
        Coordinates finalPosition = positionHandler.DetermineFinalPosition(trimmer);
        assertEquals(2, finalPosition.getX());
        assertEquals(1, finalPosition.getY());
        assertEquals("E", finalPosition.getDirection());
    }

    @Test
    public void testMoveSouth() {
        Trimmer trimmer = setUpTrimmer(1, 1, "S", "A");
        Coordinates finalPosition = positionHandler.DetermineFinalPosition(trimmer);
        assertEquals(1, finalPosition.getX());
        assertEquals(0, finalPosition.getY());
        assertEquals("S", finalPosition.getDirection());
    }

    @Test
    public void testOutOfSurfaceCommands() {
        Trimmer trimmer = setUpTrimmer(0, 0, "W", "A");
        Coordinates finalPosition = positionHandler.DetermineFinalPosition(trimmer);
        assertEquals(0, finalPosition.getX());
        assertEquals(0, finalPosition.getY());
        assertEquals("W", finalPosition.getDirection());
    }

    private Trimmer setUpTrimmer(int x, int y, String direction, String commands) {
        Coordinates coordinates = Coordinates.builder().x(x).y(y).direction(direction).build();
        Coordinates maxCoordinates = Coordinates.builder().x(5).y(5).direction(direction).build();
        return Trimmer.builder().coordinates(coordinates).maxCoordinates(maxCoordinates).commands(commands).build();
    }
}
