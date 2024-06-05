package com.kata.trimmer.steps;

import com.kata.trimmer.model.Coordinates;
import com.kata.trimmer.model.Trimmer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@Profile("test")
public class TrimmerItemReaderTest {

    @InjectMocks
    private TrimmerItemReader reader;

    private String filePath = "src/test/resources/input.txt";

    @BeforeEach
    void setUp() throws IOException {
        ReflectionTestUtils.setField(reader, "filePath", filePath);
        reader.setUpInputFile();
        reader.getSurfaceCoordinates();
    }

    @Test
    void testInit() throws IOException {

        reader.init();

        Coordinates surfaceCoordinates = reader.surfaceCoordinates;

        assertNotNull(surfaceCoordinates);
        assertEquals(5, surfaceCoordinates.getX());
        assertEquals(5, surfaceCoordinates.getY());
    }

    @Test
    void testRead() throws IOException {


        Trimmer trimmer = reader.read();

        assertNotNull(trimmer);
        assertEquals(1, trimmer.getCoordinates().getX());
        assertEquals(2, trimmer.getCoordinates().getY());
        assertEquals("N", trimmer.getCoordinates().getDirection());
        assertEquals("GAGAGAGAA", trimmer.getCommands());

        Trimmer trimmer2 = reader.read();
        assertNotEquals(trimmer, trimmer2);
    }

    @Test
    void testProcessTrimmer() {
        String positionLine = "1 2 N";
        String commandsLine = "GAGAGAGAA";

        Trimmer trimmer = reader.processTrimmer(positionLine, commandsLine);

        assertNotNull(trimmer);
        assertEquals(1, trimmer.getCoordinates().getX());
        assertEquals(2, trimmer.getCoordinates().getY());
        assertEquals("N", trimmer.getCoordinates().getDirection());
        assertEquals("GAGAGAGAA", trimmer.getCommands());
    }
}
