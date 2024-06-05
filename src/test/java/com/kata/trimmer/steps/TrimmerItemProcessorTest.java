package com.kata.trimmer.steps;

import com.kata.trimmer.model.Coordinates;
import com.kata.trimmer.model.Trimmer;
import com.kata.trimmer.service.PositionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Profile("test")
public class TrimmerItemProcessorTest {

    @InjectMocks
    private TrimmerItemProcessor processor;

    @Mock
    private PositionHandler positionHandler;

    private Trimmer trimmer;

    @BeforeEach
    void setUp() {
        Coordinates maxCoordinates = Coordinates.builder()
                .x(5)
                .y(5)
                .build();

        Coordinates initialCoordinates = Coordinates.builder()
                .x(1)
                .y(2)
                .direction("N")
                .build();

        trimmer = Trimmer.builder()
                .maxCoordinates(maxCoordinates)
                .coordinates(initialCoordinates)
                .commands("GAGAGAGAA")
                .build();
    }

    @Test
    void testProcess() {
        Coordinates expectedCoordinates = Coordinates.builder()
                .x(1)
                .y(3)
                .direction("N")
                .build();

        when(positionHandler.DetermineFinalPosition(trimmer)).thenReturn(expectedCoordinates);

        Coordinates result = processor.process(trimmer);

        assertNotNull(result);
        assertEquals(expectedCoordinates.getX(), result.getX());
        assertEquals(expectedCoordinates.getY(), result.getY());
        assertEquals(expectedCoordinates.getDirection(), result.getDirection());

        verify(positionHandler, times(1)).DetermineFinalPosition(trimmer);
    }
}
