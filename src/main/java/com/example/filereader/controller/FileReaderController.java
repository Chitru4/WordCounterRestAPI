package com.example.filereader.controller;

import com.example.filereader.filereader.WordCounter;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
public class FileReaderController {
    private final WordCounter wordCounter;
    private final Bucket bucket;
    @Autowired
    public FileReaderController(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
        Bandwidth limit = Bandwidth.classic(20, Refill.greedy(20, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }
    @GetMapping(value = "/file")
    public ResponseEntity<Map<String, String>> getFileData() throws IOException {
        Map<String, String> json = new HashMap<>();
        json.put("file_data",wordCounter.getFileData());
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(json);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @PostMapping(value = "/file")
    public void addFile(@RequestBody String buffer) throws IOException {
        wordCounter.addFile(buffer);
    }

    @PutMapping(value = "/file")
    public void addFileData(@RequestBody String newFile) throws IOException {
        wordCounter.addFileData(newFile);
    }

    @GetMapping(value = "/file/{word}")
    public ResponseEntity<Map<String, Integer>> getFrequency(@PathVariable String word) throws IOException {
        Map<String, Integer> json = new HashMap<>();
        json.put("Frequency",wordCounter.getFrequency(word));
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(json);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @DeleteMapping(value = "/file/{word}")
    public void deleteWord(@PathVariable String word) throws IOException {
        wordCounter.deleteWord(word);
    }

}
