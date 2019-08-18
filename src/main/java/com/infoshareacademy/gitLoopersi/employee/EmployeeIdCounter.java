package com.infoshareacademy.gitLoopersi.employee;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EmployeeIdCounter {

    private static final String fileName = "id.txt";
    private static long idCounter;

    public void incrementIdCounter() {

        long incrementation = Long.parseLong(getIdCounter()) + 1L;
        String content = String.valueOf(incrementation);

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(fileName))) {

            bw.write(content);

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public String getIdCounter() {

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            String line;
            if ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return sb.toString();
    }
}