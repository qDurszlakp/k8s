package com.sandbox.k8s;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;

@Slf4j
@Component
public class FileService {

    private static final String filePath = "/app/data/Data.txt";

    public void save(String content) {

        String fileName = filePath + "_" + System.currentTimeMillis();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            log.info("Attempt of writing to the file: [{}]", fileName);
            writer.append(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
