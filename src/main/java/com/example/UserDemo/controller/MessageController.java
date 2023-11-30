package com.example.UserDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class MessageController {

    private static final String MESSAGE_FILE_PATH = "src/main/resources/messages.txt";
    private static final String LOG_FILE_PATH = "src/main/resources/log.txt";

    @GetMapping("/getMessage")
    public List<String> getMessage() throws IOException {
        return Files.readAllLines(Paths.get(MESSAGE_FILE_PATH));
    }

    @GetMapping("/getMessageCount")
    public long getMessageCount() throws IOException {
        return Files.lines(Paths.get(MESSAGE_FILE_PATH)).count();
    }

    @PostMapping("/postMessage")
    public void postMessage(@RequestBody String message) throws IOException {
        Files.write(Paths.get(MESSAGE_FILE_PATH), message.getBytes());
        logActivity("New message created");
    }

    @PostMapping("/logActivity")
    public void logActivity(@RequestBody String logMessage) throws IOException {
        writeLog(logMessage);
    }

    private void writeLog(String logMessage) throws IOException {
        Files.write(Paths.get(LOG_FILE_PATH), logMessage.getBytes(), StandardOpenOption.APPEND);
    }
}
