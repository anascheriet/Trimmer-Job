package com.kata.trimmer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Trimmer {
    private Coordinates maxCoordinates;
    private Coordinates coordinates;
    private String commands;
}
