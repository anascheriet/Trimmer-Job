package com.kata.trimmer.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.trimmer.execution.ExecutionReport;
import com.kata.trimmer.model.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.batch.core.JobExecution;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TrimmerJobListenerTest {

    private TrimmerJobListener trimmerJobListener;
    private ExecutionReport executionReport;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        trimmerJobListener = new TrimmerJobListener();
        executionReport = ExecutionReport.getInstance();
        objectMapper = new ObjectMapper();
    }

    @Mock
    private JobExecution jobExecution;

    @Test
    public void testAfterJob() throws IOException {

        var coords = List
                .of(
                        Coordinates.builder().x(1).y(2).direction("N").build(),
                            Coordinates.builder().x(3).y(4).direction("S").build());
        executionReport.addToCoordinates(coords);

        trimmerJobListener.afterJob(jobExecution);

        Path path = Paths.get("src/main/resources/files/output.json");
        assertTrue(Files.exists(path));
        String fileContent = Files.readString(path);
        String expectedJson = objectMapper.writeValueAsString(executionReport.getCoordinatesList());
        assertEquals(expectedJson, fileContent);
    }
}
