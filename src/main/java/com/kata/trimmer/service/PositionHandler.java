package com.kata.trimmer.service;

import com.kata.trimmer.model.Coordinates;
import com.kata.trimmer.model.Trimmer;
import org.springframework.stereotype.Service;

@Service
public class PositionHandler {

    public Coordinates DetermineFinalPosition(Trimmer trimmer) {
        return determineFinalCoordinates(trimmer);
    }

    private Coordinates determineFinalCoordinates(Trimmer trimmer) {
        var commandsArray = trimmer.getCommands().split("");
        var currentDirection = trimmer.getCoordinates().getDirection();
        var x = trimmer.getCoordinates().getX();
        var y = trimmer.getCoordinates().getY();
        var maxX = trimmer.getMaxCoordinates().getX();
        var maxY = trimmer.getMaxCoordinates().getY();
        for (String c : commandsArray) {
            if (c.equals("G") || c.equals("D")) {
                currentDirection = determineNextDirection(currentDirection, c);
            }
            else if (c.equals("A")) {
                switch (currentDirection) {
                case "N":
                    if (y < maxY)
                        y++;
                    break;
                case "S":
                    if (y > 0)
                        y--;
                    break;
                case "E":
                    if (x < maxX)
                        x++;
                    break;
                case "W":
                    if (x > 0)
                        x--;
                    break;
                }
            }
        }
        trimmer.getCoordinates().setDirection(currentDirection);
        trimmer.getCoordinates().setX(x);
        trimmer.getCoordinates().setY(y);
        return trimmer.getCoordinates();
    }

    private String determineNextDirection(String currentDirection, String command) {
        switch (command) {
            case "G":
                switch (currentDirection) {
                    case "N":
                        return "W";
                    case "W":
                        return "S";
                    case "S":
                        return "E";
                    case "E":
                        return "N";
                    default:
                        return currentDirection;
                }
            case "D":
                switch (currentDirection) {
                    case "N":
                        return "E";
                    case "W":
                        return "N";
                    case "S":
                        return "W";
                    case "E":
                        return "S";
                    default:
                        return currentDirection;
                }
            default:
                return currentDirection;
        }
    }

}
