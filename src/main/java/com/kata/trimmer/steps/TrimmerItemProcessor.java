package com.kata.trimmer.steps;

import com.kata.trimmer.model.Coordinates;
import com.kata.trimmer.model.Trimmer;
import com.kata.trimmer.service.PositionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TrimmerItemProcessor implements ItemProcessor<Trimmer, Coordinates> {

    private final PositionHandler positionHandler;

    @Override
    public Coordinates process(Trimmer trimmer) {
        return positionHandler.DetermineFinalPosition(trimmer);
    }
}
