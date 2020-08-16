package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) throws Exception {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while (in.ready()) {
                String s = in.readLine();
                if (is404(s, 2)) {
                    try {
                        if (Integer.parseInt(s.split(" ")[getSize(s) - 1]) != 404) {
                            rsl.add(s);
                        }

                    } catch (NumberFormatException e) {
                    }

                }
            }
        }

        return rsl;
    }

    private static boolean is404(String s, int i) throws IOException {
        return s.split(" ")[getSize(s) - i].contains("404");
    }

    private static int getSize(String s) throws IOException {
        return s.split(" ").length;

    }


    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            StringBuilder sb = new StringBuilder();
            log.forEach(sb::append);
            out.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
