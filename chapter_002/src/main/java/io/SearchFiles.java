package io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {
    private final List<Path> searchPaths = new ArrayList<>();
    private final Predicate<Path> predicate;

    public SearchFiles(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (predicate.test(file)) {
            searchPaths.add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        return searchPaths;
    }
}
