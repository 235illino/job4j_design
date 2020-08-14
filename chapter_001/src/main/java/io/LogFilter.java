package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> lines = new ArrayList<>();
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {

            in.lines().forEach(lines::add);
            for (String s : lines) {
                if (s.contains("404")) {
                    List<String> tmp = Arrays.stream(s.split(" ")).collect(Collectors.toList());
                    try {
                        if (Integer.parseInt(tmp.get(tmp.size() - 1)) != 404) {
                            rsl.add(s);
                        };
                    } catch (NumberFormatException e) {
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
