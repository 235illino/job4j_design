package io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleChat {
    private List<String> answers = new ArrayList<>();
    private final String BREAK_SPEAKING = "стоп";
    private final String STOP_SPEAKING = "закончить";

    public void speaking(String logPath, String ansPath) {

        try (BufferedReader consoleReder
                     = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(logPath)));
             BufferedReader inAns
                     = new BufferedReader(new FileReader(ansPath))) {
            System.out.println("write smth");
            List<String> listAns = Arrays.stream(inAns.readLine().split(" ")).collect(Collectors.toList());
            while (consoleReder.ready()) {
                String s = consoleReder.readLine();
                if (s.equals(BREAK_SPEAKING)) {
                    answers.add(s);
                } else if (s.equals(STOP_SPEAKING)) {
                    answers.add(s);
                    break;
                } else {
                    System.out.println(generateAnswer(listAns));
                    answers.add(s);
                }
            }
            StringBuilder sb = new StringBuilder();
            answers.forEach(s -> sb.append(s.trim()).append(System.lineSeparator()));
            out.write(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String generateAnswer(List<String> list) throws IOException {
        String s = list.get((int) (Math.random() * (list.size() - 1)));
        answers.add(s);
        return s;
    }


    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat();
        consoleChat.speaking("./405.txt", "./404.txt");
    }


}
