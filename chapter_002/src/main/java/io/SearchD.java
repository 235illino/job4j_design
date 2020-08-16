package io;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchD extends SimpleFileVisitor<Path> {
    private final Map<String, List<String>> map = new HashMap<>();

    public List<List<String>> getDuplicates() {
        return map.values().stream().filter(v -> v.size() > 1).collect(Collectors.toList());
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        String keyFile = file.getFileName().toString() + " " + file.toFile().length();
        if (map.containsKey(keyFile)) {
            map.merge(keyFile, map.get(keyFile), (key, list) -> {
                List<String> fileList = new ArrayList<>(list);
                fileList.add(file.toAbsolutePath().toString());
                return fileList;
            });
        } else {
            List<String> list = new ArrayList<>();
            list.add(file.toAbsolutePath().toString());
            map.put(keyFile, list);
        }
        return CONTINUE;
    }

}

