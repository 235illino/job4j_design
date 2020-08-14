package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> lines = new ArrayList<>();
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)
                     ))) {

            in.lines().forEach(lines::add);
            int change = 0;
            for (int i = 0; i < lines.size(); i++) {

                if (isUnavai(lines.get(i)) && change == 0) {
                    rsl.add(lines.get(i).split(" ")[1] + ";");
                    change++;
                }
                if (!isUnavai(lines.get(i)) && change > 0) {
                    rsl.add(lines.get(i).split(" ")[1] + "; ");
                    change = 0;
                }
            }

            rsl.forEach(out::write);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private boolean isUnavai(String s) {
        return Integer.parseInt(s.split(" ")[0]) == 400 || Integer.parseInt(s.split(" ")[0]) == 500;
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}