package io;
import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (FileOutputStream fos = new FileOutputStream(target)) {
            byte[] buffer = new byte[1024];
            try (ZipOutputStream zos = new ZipOutputStream(fos)) {
                for (Path source : sources) {
                    FileInputStream fis = new FileInputStream(source.toFile());
                    String path;
                    if (source.toFile().getAbsolutePath().split(":\\\\").length > 0) {
                        path = source.toFile().getAbsolutePath().split(":\\\\")[1];
                    } else {
                        path = source.toFile().getAbsolutePath();
                    }
                    zos.putNextEntry(new ZipEntry(path));
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
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

    public static void main(String[] args) {
        new Zip().packSingleFile(
                new File("./chapter_005/pom.xml"),
                new File("./chapter_005/pom.zip")
        );
    }
}
