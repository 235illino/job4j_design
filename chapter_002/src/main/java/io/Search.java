package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("check args");
        }
        Path start = Paths.get(args[0]);
        search(start, args[1]).forEach(System.out::println);
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        return search(root, ext, null);
    }

    public static List<Path> search(Path root, String include, String exclude) throws IOException {
        SearchFiles searchFiles = new SearchFiles().include(include).exclude(exclude);
        Files.walkFileTree(root, searchFiles);
        return searchFiles.getPaths();
    }

}
