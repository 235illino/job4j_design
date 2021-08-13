package io.archiver;
import io.ArgZip;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toString())))) {
            for (Path p : sources) {
                try {
                    zip.putNextEntry(new ZipEntry(p.toString()));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(p.toString()))) {
                        zip.write(out.readAllBytes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgZip zipParameters = new ArgZip(args);
        if (!zipParameters.valid()) {
            throw new IllegalStateException("Wrong arguments. Usage java -jar Zip.jar -d SOURCE_PATH -e *.java -o project.zip");

        }
        List<Path> searchResult = Search.search(Path.of(zipParameters.directory()), null, zipParameters.exclude());

        new Zip().packFiles(searchResult, Path.of(zipParameters.output()));
        new Zip().packSingleFile(
                new File("./chapter_005/pom.xml"),
                new File("./chapter_005/pom.zip")
        );
    }
}
