package com.kata.trimmer.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Coordinates implements Serializable {
    private static final long serialVersionUID = 1L;

    private int x;
    private int y;
    private String direction;
}
