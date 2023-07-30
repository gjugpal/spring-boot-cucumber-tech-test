package com.geek.helpers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporter {

    public static ResponseEntity<String> getReport(String location) {

        String filename = copyReportAndDeleteOldOne(location, "Index.html");

        File reportFile = new File(filename);
        if (reportFile.exists()) {
            try {
                String content = new String(Files.readAllBytes(reportFile.toPath()));
                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_HTML)
                        .body(content);
            } catch (IOException e) {
                return ResponseEntity.status(500).body("Error reading the report.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private static String copyReportAndDeleteOldOne(String location, String filename) {

        String sourceFileName = location + filename;
        String destinationFileName = location + "test-report-" + getCurrentDateTime() + ".html";

        try {
            // Create Path objects for source and destination files
            Path sourceFile = Paths.get(sourceFileName);
            Path destinationFile = Paths.get(destinationFileName);

            // Copy the file to the destination
            Files.copy(sourceFile, destinationFile);

            // Delete the original file
            Files.delete(sourceFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return destinationFileName;
    }

    private static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        return dateFormat.format(new Date());
    }

}
