package io;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            List<Integer> even2 = new ArrayList<>();
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                even2.add(Integer.parseInt(line));
            }
            for (Integer i : even2) {
                if (i % 2 == 0) {
                    System.out.println("even value " + i);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
