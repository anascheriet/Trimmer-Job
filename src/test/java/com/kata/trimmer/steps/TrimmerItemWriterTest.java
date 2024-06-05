package com.kata.trimmer.steps;

import com.kata.trimmer.execution.ExecutionReport;
import com.kata.trimmer.model.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TrimmerItemWriterTest {

    private TrimmerItemWriter trimmerItemWriter;
    private ExecutionReport executionReport;

    @BeforeEach
    public void setUp() {
        executionReport = ExecutionReport.getInstance();
        trimmerItemWriter = new TrimmerItemWriter();
    }

    @Test
    public void testWrite() {
        List<Coordinates> coordinatesList = new ArrayList<>();
        coordinatesList.add(Coordinates.builder().x(1).y(2).direction("N").build());
        coordinatesList.add(Coordinates.builder().x(3).y(4).direction("S").build());

        trimmerItemWriter.write(coordinatesList);

        assertEquals(coordinatesList, executionReport.getCoordinatesList());
    }
}
