package com.sandbox.k8s;

import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Component
public class FileService {

    public void save(String content) {

        try (PrintWriter writer = new PrintWriter("/app/data/Data.txt", StandardCharsets.UTF_8)) {
            writer.append(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
