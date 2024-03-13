package com.airtel.filereader.filereader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class for WordCounter.
 * It is used for retrieving/appending/deleting data from a given file (file.txt).
 * The getFrequency method obtains the frequency of the word in the file.
 * The deleteWord method deletes all occurrences of the word in the file.
 */
@Component
public class WordCounter {
    private static final String FILE_PATH = "file.txt";
    private static int requiredWordFrequency = 0;

    public String getFileData() throws IOException {
        StringBuilder buffer = new StringBuilder();
        try (Scanner sc = new Scanner(new ClassPathResource(FILE_PATH).getFile())) {
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine());
            }
        }
        return buffer.toString();
    }

    public int getFrequency(String requiredWord) throws IOException {
        try (Scanner sc = new Scanner(new ClassPathResource(FILE_PATH).getFile())) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] words = line.split(" ");
                for (String word : words) {
                    String changedWord = removePunctuations(word);
                    if (changedWord.equals(requiredWord)) {
                        requiredWordFrequency++;
                    }
                    System.out.println(word);
                }
            }
        }
        return requiredWordFrequency;
    }

    public void addFile(String buffer) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(new ClassPathResource(FILE_PATH).getFile()))) {
            out.write(buffer);
        }
    }

    public void deleteWord(String requiredWord) throws IOException {
        StringBuilder buffer = new StringBuilder();
        try (Scanner sc = new Scanner(new ClassPathResource(FILE_PATH).getFile())) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] words = line.split(" ");
                for (String word : words) {
                    String changedWord = removePunctuations(word);
                    if (!word.equals(requiredWord)) {
                        buffer.append(" ").append(changedWord);
                    }
                }
            }
        }
        try (PrintWriter out = new PrintWriter(new FileWriter(new ClassPathResource(FILE_PATH).getFile()))) {
            out.println(buffer.toString().trim());
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    public void addFileData(String newFile) throws IOException {
        StringBuilder buffer = new StringBuilder();
        try (Scanner sc = new Scanner(new ClassPathResource(newFile).getFile())) {
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine());
            }
        }
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new ClassPathResource(FILE_PATH).getFile(), true)))) {
            out.println(buffer);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    public String removePunctuations(String word) {
        word = word.toLowerCase();
        if (!word.isEmpty() && ",.?!".indexOf(word.charAt(word.length() - 1)) != -1) {
            word = word.substring(0, word.length() - 1);
        }
        return word;
    }

    private void handleIOException(IOException e) {
        System.err.println("Error encountered: " + e.getMessage());
    }
}

