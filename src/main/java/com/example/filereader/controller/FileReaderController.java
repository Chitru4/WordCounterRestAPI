package com.example.filereader.controller;

import com.example.filereader.filereader.WordCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1")
public class FileReaderController {
    WordCounter wordCounter;
    @Autowired
    public FileReaderController(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }
    @GetMapping(value = "/file")
    public String getFileData() throws IOException {
        return wordCounter.getFileData();
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
    public Integer getFrequency(@PathVariable String word) throws IOException {
        return wordCounter.getFrequency(word);
    }

    @DeleteMapping(value = "/fileDel/{word}")
    public void deleteWord(@PathVariable String word) throws IOException {
        wordCounter.deleteWord(word);
    }

}
