package io.archiver;

import io.SearchD;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SearchDuplicate {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get("./");
        search(start).forEach(System.out::println);
    }


    public static List<List<String>> search(Path root) throws IOException {
        SearchD searcher = new SearchD();
        Files.walkFileTree(root, searcher);
        return searcher.getDuplicates();
    }
}
