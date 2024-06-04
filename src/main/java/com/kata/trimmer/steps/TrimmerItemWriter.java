package com.kata.trimmer.steps;

import com.kata.trimmer.model.Coordinates;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrimmerItemWriter implements ItemWriter<Coordinates> {

    @Override
    public void write(List<? extends Coordinates> coordinates) {
        for (var coors : coordinates) {
            System.out.println(coors.getX() + " " + coors.getY() + " " + coors.getDirection());
        }
    }
}
