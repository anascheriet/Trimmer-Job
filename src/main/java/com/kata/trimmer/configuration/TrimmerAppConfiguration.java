package com.kata.trimmer.configuration;

import com.kata.trimmer.model.Coordinates;
import com.kata.trimmer.model.Trimmer;
import com.kata.trimmer.steps.TrimmerItemProcessor;
import com.kata.trimmer.steps.TrimmerItemReader;
import com.kata.trimmer.steps.TrimmerItemWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class TrimmerAppConfiguration {

    private final StepBuilderFactory stepBuilder;
    private final JobBuilderFactory jobBuilderFactory;

    private final TrimmerItemReader reader;
    private final TrimmerItemProcessor processor;
    private final TrimmerItemWriter writer;

    @Value("${chunk}")
    Integer chunkSize;

    @Bean("trimmer-job")
    public Job job() {
        Step processTrimmers = stepBuilder
                .get("trimmer-positions")
                    .<Trimmer, Coordinates> chunk(chunkSize)
                    .reader(reader)
                    .processor(processor)
                    .writer(writer)
                    .build();
        return jobBuilderFactory.get("trimmer-job").start(processTrimmers).preventRestart().build();

    }

}
