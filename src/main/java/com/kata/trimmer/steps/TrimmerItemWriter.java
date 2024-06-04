package com.kata.trimmer.steps;

import com.kata.trimmer.execution.ExecutionReport;
import com.kata.trimmer.model.Coordinates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TrimmerItemWriter implements ItemWriter<Coordinates> {
    private final ExecutionReport executionReport = ExecutionReport.getInstance();

    @Override
    public void write(List<? extends Coordinates> coordinates) {
        executionReport.addToCoordinates((List<Coordinates>) coordinates);
        for (var coors : coordinates) {
            log.info(coors.getX() + " " + coors.getY() + " " + coors.getDirection());
        }
    }
}
