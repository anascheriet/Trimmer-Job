package com.kata.trimmer.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.trimmer.execution.ExecutionReport;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class TrimmerJobListener implements JobExecutionListener {

    private final ExecutionReport executionReport = ExecutionReport.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Starting Trimmer Job");
    }

    @SneakyThrows
    @Override
    public void afterJob(JobExecution jobExecution) {
        WriteToOutputFile();
        log.info("Finished Trimmer Job.");
    }

    private void WriteToOutputFile() throws IOException {
        log.info("Writing to output file ...");
        Path path = Paths.get("src/main/resources/files/output.json");

        // Create the file if it doesn't exist
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Write objects to the file
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path.toFile()), "UTF-8"))) {
            String json = objectMapper.writeValueAsString(executionReport.getCoordinatesList());
            writer.write(json);
        }
    }
}
