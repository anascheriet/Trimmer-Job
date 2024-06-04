package com.kata.trimmer.steps;

import com.kata.trimmer.model.Coordinates;
import com.kata.trimmer.model.Trimmer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Slf4j
@RequiredArgsConstructor
public class TrimmerItemReader implements ItemReader<Trimmer> {

    private BufferedReader reader;
    public Coordinates surfaceCoordinates;
    @Value("${input.file.path}")
    String filePath;

    @BeforeStep
    void init() throws IOException {
        setUpInputFile();
        getSurfaceCoordinates();
    }
    private void setUpInputFile() throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Resource resource = new ClassPathResource("files/input.txt");
            reader = new BufferedReader(new FileReader(resource.getFile().getPath()));
        }
        else {
            reader = new BufferedReader(new FileReader(filePath));
        }

    }

    private void getSurfaceCoordinates() throws IOException {

        String[] surfaceDimensions = reader.readLine().split(" ");
        surfaceCoordinates = Coordinates
                .builder()
                .x(Integer.parseInt(surfaceDimensions[0]))
                .y(Integer.parseInt(surfaceDimensions[1]))
                .build();
    }

    @Override
    public Trimmer read() throws IOException {
        String nextPositionLine = reader.readLine();
        if (nextPositionLine == null) {
            reader.close();
            return null;
        }

        String nextCommandsLine = reader.readLine();
        if (nextCommandsLine == null) {
            reader.close();
            return null;
        }
        return processTrimmer(nextPositionLine, nextCommandsLine);
    }

    private Trimmer processTrimmer(String positionLine, String commandsLine) {
        String[] initialPosition = positionLine.split(" ");
        var trimmerCoords = Coordinates
                .builder()
                    .x(Integer.parseInt(initialPosition[0]))
                    .y(Integer.parseInt(initialPosition[1]))
                    .direction(String.valueOf(initialPosition[2].charAt(0)))
                    .build();
        return Trimmer
                .builder()
                    .maxCoordinates(surfaceCoordinates)
                    .coordinates(trimmerCoords)
                    .commands(commandsLine)
                    .build();
    }

}
