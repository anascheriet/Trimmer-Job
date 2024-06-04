package com.kata.trimmer.execution;

import com.kata.trimmer.model.Coordinates;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ExecutionReport {

    @Getter
    private static final ExecutionReport instance = new ExecutionReport();

    private final List<Coordinates> coordinatesList = new ArrayList<>();

    ExecutionReport() {
    }

    public void addToCoordinates(List<Coordinates> coordinates) {
        coordinatesList.addAll(coordinates);
    }
}
