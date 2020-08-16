package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) throws IOException {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)
                     ))) {
            int change = 0;
            while (in.ready()) {
                String s = in.readLine();
                if (isUnavai(s) && change == 0) {
                    rsl.add(s.split(" ")[1] + ";");
                    change++;
                }
                if (!isUnavai(s) && change > 0) {
                    rsl.add(s.split(" ")[1] + "; ");
                    change = 0;
                }
            }
            StringBuilder sb = new StringBuilder();
            rsl.forEach(s -> sb.append(s.trim()));
            out.write(sb.toString());
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