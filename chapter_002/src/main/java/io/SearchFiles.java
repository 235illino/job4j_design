package io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {
    private final List<Path> searchPaths = new ArrayList<>();
    private String include;
    private String exclude;


    public SearchFiles include(String include) {
        this.include = include != null ? include.replace("*", "") : null;
        return this;
    }

    public SearchFiles exclude(String exclude) {
        this.exclude = exclude != null ? exclude.replace("*", "") : null;
        return this;
    }


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (fileCorrect(file)) {
            searchPaths.add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        return searchPaths;
    }

    private boolean fileCorrect(Path file) {
        if (include != null && !file.toString().endsWith(include)) {
            return false;
        }
        if (exclude != null && file.toString().endsWith(exclude)) {
            return false;
        }
        return true;
    }
}
