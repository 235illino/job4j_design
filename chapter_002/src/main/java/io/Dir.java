package io;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("/home/ilya/IdeaProjects/job4j_design");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File f : Objects.requireNonNull(file.listFiles()))
        {
           if (f.isFile()) {
               System.out.println(f.getName() + " " + f.getTotalSpace());
           }
        }
    }
}
