package com.geek.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CucumberFiles {

    public static List<String> getFeatureFilenames() {
        Path resourceDirectory = Paths.get("src","test","resources", "features");
        List<String> filenames = new ArrayList<>();
        // Read the files in the directory
        try {
            Path resourcePath = Paths.get(resourceDirectory.toUri());
            Files.walk(resourcePath)
                    .filter(Files::isRegularFile)
                    .forEach(file -> filenames.add(file.getFileName().toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return filenames;
    }
}
